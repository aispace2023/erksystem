package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.rmq.module.handler.api.*;
import com.erksystem.protobuf.api.ErkApiMsg;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.module.RmqLogPrinter.*;


/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkApiMsgRmqConsumer {
    private ErkApiMsgRmqConsumer() {
    }

    public static void consumeMessage(byte[] message) {
        try {
            ErkApiMsg msg = ErkApiMsg.parseFrom(message);
            log.info("RMQ Recv [{}]", proto2Json(msg).orElse("Fail to Parse"));
            switch (msg.getMsgCase()) {
                case ERKSERVICECONNRQ -> new ErkServiceConnRqHandler().proc(msg);
                case ERKSERVICEDISCONNRQ -> new ErkServiceDisConnRqHandler().proc(msg);
                case EMORECOGCREATERQ -> new EmoRecogCreateRqHandler().proc(msg);
                case EMORECOGDELETERQ -> new EmoRecogDeleteRqHandler().proc(msg);
                case HBRQ -> new HbRqHandler().proc(msg);
                case EMORECOGRQ -> new EmoRecogRqHandler().proc(msg);
                case PHYSIOEMORECOGRQ -> new PhysioEmoRecogRqHandler().proc(msg);
                case SPEECHEMORECOGRQ -> new SpeechEmoRecogRqHandler().proc(msg);
                case FACEEMORECOGRQ -> new FaceEmoRecogRqHandler().proc(msg);
                default -> log.warn("Recv Undefined type [{}]", msg.getMsgCase());
            }
        } catch (Exception e) {
            log.warn("Err Occurs while consume ErkMessage", e);
        }
    }
}
