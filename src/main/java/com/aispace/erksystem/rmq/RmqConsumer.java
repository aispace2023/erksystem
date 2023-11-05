package com.aispace.erksystem.rmq;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author kangmoo Heo
 */
@Slf4j
public abstract class RmqConsumer {
    private final ScheduledExecutorService executor;
    private final ArrayBlockingQueue<byte[]> queue;

    protected RmqConsumer(int consumerCount, int rmqBufferCount) {
        executor = Executors.newScheduledThreadPool(consumerCount, new BasicThreadFactory.Builder()
                .namingPattern("RMQ_CONSUMER-%d")
                .daemon(true)
                .priority(Thread.MAX_PRIORITY)
                .build());
        queue = new ArrayBlockingQueue<>(rmqBufferCount);

        for (int i = 0; i < consumerCount; i++) {
            executor.scheduleWithFixedDelay(() -> {
                try {
                    while (true) {
                        byte[] data = queue.poll();
                        if (data == null) break;
                        handleRmqMessage(data);
                    }
                } catch (Exception e) {
                    log.warn("Err Occurs while handling RMQ Message", e);
                }
            }, 0, 10, TimeUnit.MILLISECONDS);
        }
    }

    public void consume(byte[] msg) {
        try {
            if (!this.queue.offer(msg)) {
                log.warn("RMQ RCV Queue full. Drop message.");
            }
        } catch (Exception e) {
            log.warn("Err Occurs", e);
        }
    }

    public void close() {
        if (this.executor != null && !this.executor.isShutdown()) {
            this.executor.shutdown();
        }
    }

    public abstract void handleRmqMessage(byte[] data);
}
