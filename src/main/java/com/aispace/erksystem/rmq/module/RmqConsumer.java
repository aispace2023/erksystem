package com.aispace.erksystem.rmq.module;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqConsumer {
    private RmqConsumer() {
    }

    public static void consumeMessage(byte[] message) {
        log.info("Message Received [{}]", new String(message));
        // TODO : Add consume job
    }
}
