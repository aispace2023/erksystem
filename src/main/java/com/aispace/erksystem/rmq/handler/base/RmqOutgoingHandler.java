package com.aispace.erksystem.rmq.handler.base;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.service.AppInstance;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.module.RmqLogPrinter.getMsgFrom;
import static com.aispace.erksystem.rmq.module.RmqLogPrinter.proto2Json;


/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqOutgoingHandler {
    private static final AppInstance appInstance = AppInstance.getInstance();
    private static final UserConfig userConfig = appInstance.getUserConfig();
    private static final RmqManager rmqManager = RmqManager.getInstance();

    private RmqOutgoingHandler() {
    }

    public static void send(Message msg, String queueName) {
        try {
            rmqManager.getRmqModule().sendMessage(queueName, msg.toByteArray(), 60_000);
            log.info("[RMQ MESSAGE SEND] ERK_PLATFORM->{} [{}]", queueName, proto2Json(msg).orElse("Fail to Parse"));
        } catch (Exception e) {
            log.warn("Err Occurs", e);
        }
    }

    public static void sendToApi(Message msg) {
        send(msg, userConfig.getRmqOutgoingQueueApi());
    }

    public static void sendToSubSystem(Message msg) {
        send(msg, userConfig.getRmqOutgoingQueueSubsystem());
    }

    public static void sendErkApiMsg2API(Message msg) {
        sendToApi(ErkMsgWrapper.wrap2ErkApiMsg(msg));
    }

    public static void sendErkApiMsg2SubSystem(Message msg) {
        sendToApi(ErkMsgWrapper.wrap2ErkInterApiMsg(msg));
    }

}
