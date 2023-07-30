package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.module.RmqConsumer;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.aispace.erksystem.service.AppInstance;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqManager {
    private static final UserConfig config = AppInstance.getInstance().getConfig();
    private static RmqModule rmqModule;

    private RmqManager() {
    }

    @Synchronized
    public static void start() throws IOException, TimeoutException, IllegalStateException {
        if (rmqModule != null) {
            throw new IllegalStateException("Already Started");
        }

        rmqModule = new RmqModule(config.getRmqHost(), config.getRmqUser(), config.getRmqPassword());
        rmqModule.connect(() -> log.info("RMQ Conneted"), () -> log.warn("RMQ Disconnected"));
        for (String incomingQueue : config.getIncomingQueues()) {
            rmqModule.queueDeclare(incomingQueue);
            rmqModule.registerByteConsumer(incomingQueue, RmqConsumer::consumeMessage);
        }
    }

    @Synchronized
    public static void stop() {
        try {
            rmqModule.close();
            rmqModule = null;
        } catch (Exception e) {
            log.warn("Fail to close RMQ", e);
        }
    }

    public static void sendMessage(String queueName, byte[] message) throws IOException {
        rmqModule.sendMessage(queueName, message);
    }
}
