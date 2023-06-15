package com.aispace.erksystem.rmq.module;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqConsumer {
    public static void consumeMessage(String message) {
        log.info("Message Received [{}]", message);
        // TODO : Add consume job
    }
}
