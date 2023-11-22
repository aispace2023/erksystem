package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.rmq.module.handler.prov.*;
import com.erksystem.protobuf.prov.ErkProvMsg;
import com.erksystem.protobuf.prov.ErkProvMsg_m;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.module.RmqLogPrinter.proto2Json;


/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkProvMsgRmqConsumer {
    private ErkProvMsgRmqConsumer() {
    }

    public static void consumeMessage(byte[] message) {
        try {
            ErkProvMsg_m msg = ErkProvMsg_m.parseFrom(message);
            log.info("RMQ Recv [{}]", proto2Json(msg).orElse("Fail to Parse"));
            switch (msg.getMsgCase()) {
                case ADDSERVICEPROVIDERINFORQ -> new AddServiceProviderInfoRQHandler().proc(msg);
                case UPDATESERVICEPROVIDERINFORQ -> new UpdateServiceProviderInfoRQHandler().proc(msg);
                case DELSERVICEPROVIDERINFORQ -> new DelServiceProviderInfoRQHandler().proc(msg);
                case ADDUSERINFORQ -> new AddUserInfoRQHandler().proc(msg);
                case UPDATEUSERINFORQ -> new UpdateUserInfoRQHandler().proc(msg);
                case DELUSERINFORQ -> new DelUserInfoRQHandler().proc(msg);
                default -> log.warn("Recv Undefined type [{}]", msg.getMsgCase());
            }
        } catch (Exception e) {
            log.warn("Err Occurs while consume ErkMessage", e);
        }
    }
}
