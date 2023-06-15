package com.aispace.erksystem.rmq.module;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqServer {
    private final RmqService rmqConnection;
    private ExecutorService rmqHandlers;

    public RmqServer(String rmqHost, String rmqUser, String rmqPass) {
        rmqConnection = new RmqService(rmqHost, rmqUser, rmqPass);
    }

    public void connect(int rmqHandlerSize) {
        rmqConnection.connect();
        rmqHandlers = Executors.newFixedThreadPool(rmqHandlerSize, new BasicThreadFactory.Builder().namingPattern("RMQ_HANDLER").build());
    }

    public void disconnect() {
        rmqConnection.close();
    }

    public void addConsumer(String queueName) throws IOException {
        rmqConnection.queueDeclare(queueName);
        rmqConnection.registerStringConsumer(queueName, this::onMessageReceived);
    }

    public void onMessageReceived(String message) {
        rmqHandlers.execute(() -> {
            try {
                RmqConsumer.consumeMessage(message);
            } catch (Exception e) {
                log.warn("Error Occurs while handling message", e);
            }
        });
    }
}
