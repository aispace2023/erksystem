package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.module.RmqConsumer;
import com.aispace.erksystem.rmq.module.RmqService;
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
    private static RmqService rmqService;

    private RmqManager() {
    }

    @Synchronized
    public static void start() throws IOException, TimeoutException, IllegalStateException {
        if (rmqService != null) {
            throw new IllegalStateException("Already Started");
        }

        rmqService = new RmqService(config.getRmqHost(), config.getRmqUser(), config.getRmqPassword());
        rmqService.connect();
        for (String incomingQueue : config.getIncomingQueues()) {
            rmqService.queueDeclare(incomingQueue);
            rmqService.registerByteConsumer(incomingQueue, RmqConsumer::consumeMessage);
        }
    }

    @Synchronized
    public static void stop() {
        try {
            rmqService.close();
            rmqService = null;
        } catch (Exception e) {
            log.warn("Fail to close RMQ", e);
        }
    }

    public static void sendMessage(String queueName, byte[] message) throws IOException {
        rmqService.sendMessage(queueName, message);
    }
}
