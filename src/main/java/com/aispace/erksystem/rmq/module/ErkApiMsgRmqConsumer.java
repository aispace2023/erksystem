package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.handler.api.connection.ErkServiceConnRqHandler;
import com.aispace.erksystem.rmq.handler.api.connection.ErkServiceDisConnRqHandler;
import com.aispace.erksystem.rmq.handler.api.connection.HeartBeatRqHandler;
import com.aispace.erksystem.rmq.handler.api.crud.*;
import com.aispace.erksystem.rmq.handler.api.engine.EmoRecogRqHandler;
import com.aispace.erksystem.rmq.handler.api.engine.FaceEmoRecogRqHandler;
import com.aispace.erksystem.rmq.handler.api.engine.PhysioEmoRecogRqHandler;
import com.aispace.erksystem.rmq.handler.api.engine.SpeechEmoRecogRqHandler;
import com.aispace.erksystem.rmq.handler.api.service.EmoServiceStartRQHandler;
import com.aispace.erksystem.rmq.handler.api.service.EmoServiceStopRQHandler;
import com.aispace.erksystem.rmq.handler.subsystem.ErkEngineCreateRPHandler;
import com.aispace.erksystem.rmq.handler.subsystem.ErkEngineDeleteRPHandler;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.ErkApiMsg;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.module.RmqLogPrinter.getMsgFrom;
import static com.aispace.erksystem.rmq.module.RmqLogPrinter.proto2Json;


/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkApiMsgRmqConsumer {
    private static final UserConfig userConfig = AppInstance.getInstance().getUserConfig();
    private ErkApiMsgRmqConsumer() {
    }

    public static void consumeApiMessage(byte[] message) {
        try {
            ErkApiMsg msg = ErkApiMsg.parseFrom(message);
            log.info("[RMQ MESSAGE RECV] {}<-{} [{}]", userConfig.getRmqIncomingQueueApi(), getMsgFrom(msg), proto2Json(msg).orElse("Fail to Parse"));
            switch (msg.getMsgCase()) {
                case ERKSERVICECONNRQ -> new ErkServiceConnRqHandler().proc(msg);
                case ERKSERVICEDISCONNRQ -> new ErkServiceDisConnRqHandler().proc(msg);
                case HEARTBEATRQ -> new HeartBeatRqHandler().proc(msg);
                case EMORECOGRQ -> new EmoRecogRqHandler().proc(msg);
                case PHYSIOEMORECOGRQ -> new PhysioEmoRecogRqHandler().proc(msg);
                case SPEECHEMORECOGRQ -> new SpeechEmoRecogRqHandler().proc(msg);
                case FACEEMORECOGRQ -> new FaceEmoRecogRqHandler().proc(msg);
                case ADDSERVICEPROVIDERINFORQ -> new AddServiceProviderInfoRQHandler().proc(msg);
                case DISSERVICEPROVIDERINFORQ -> new DisServiceProviderInfoRQHandler().proc(msg);
                case UPDATESERVICEPROVIDERINFORQ -> new UpdateServiceProviderInfoRQHandler().proc(msg);
                case DELSERVICEPROVIDERINFORQ -> new DelServiceProviderInfoRQHandler().proc(msg);
                case ADDUSERINFORQ -> new AddUserInfoRQHandler().proc(msg);
                case DISUSERINFORQ -> new DisUserInfoRQHandler().proc(msg);
                case UPDATEUSERINFORQ -> new UpdateUserInfoRQHandler().proc(msg);
                case DELUSERINFORQ -> new DelUserInfoRQHandler().proc(msg);
                case EMOSERVICESTARTRQ -> new EmoServiceStartRQHandler().proc(msg);
                case EMOSERVICESTOPRQ -> new EmoServiceStopRQHandler().proc(msg);
                default -> log.warn("Recv Undefined type [{}]", msg.getMsgCase());
            }
        } catch (Exception e) {
            log.warn("Err Occurs while consume ErkMessage", e);
        }
    }

    public static void consumeSubsystemApiMessage(byte[] message) {
        try {
            ErkApiMsg msg = ErkApiMsg.parseFrom(message);
            log.info("[RMQ MESSAGE RECV] {}<-{} [{}]", userConfig.getRmqIncomingQueueApi(), getMsgFrom(msg), proto2Json(msg).orElse("Fail to Parse"));
            switch (msg.getMsgCase()) {
                case ERKENGINECREATERP -> new ErkEngineCreateRPHandler().proc(msg);
                case ERKENGINEDELETERP -> new ErkEngineDeleteRPHandler().proc(msg);
                default -> log.warn("Recv Undefined type [{}]", msg.getMsgCase());
            }
        } catch (Exception e) {
            log.warn("Err Occurs while consume ErkMessage", e);
        }
    }
}
