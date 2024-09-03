package com.aispace.erksystem.rmq.handler.base;

import com.erksystem.protobuf.api.*;
import com.erksystem.protobuf.api.ErkApiMsg;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkMsgWrapper {
    private ErkMsgWrapper() {
    }

    public static ErkApiMsg wrap2ErkApiMsg(Message message) {
        ErkApiMsg.Builder builder = ErkApiMsg.newBuilder();
        try {
            Method method = Arrays.stream(builder.getClass().getDeclaredMethods())
                    .filter(o -> o.getName().startsWith("set"))
                    .filter(o -> o.getParameterTypes().length == 1)
                    .filter(o -> o.getParameterTypes()[0].equals(message.getClass()))
                    .findAny()
                    .orElseThrow();
            method.invoke(builder, message);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fail to wrap2ErkApiMsg");
        }

        return builder.build();
    }

    public static Optional<com.google.protobuf.ProtocolMessageEnum> getType(Message message) {
        return switch (message.getClass().getSimpleName()) {
            case "AddServiceProviderInfoRQ_m" -> Optional.of(ErkMsgType_e.AddServiceProviderInfoRQ);
            case "AddServiceProviderInfoRP_m" -> Optional.of(ErkMsgType_e.AddServiceProviderInfoRP);
            case "UpdateServiceProviderInfoRQ_m" -> Optional.empty();
            case "UpdateServiceProviderInfoRP_m" -> Optional.empty();
            case "DelServiceProviderInfoRQ_m" -> Optional.of(ErkMsgType_e.DelServiceProviderInfoRQ);
            case "DelServiceProviderInfoRP_m" -> Optional.of(ErkMsgType_e.DelServiceProviderInfoRP);
            case "AddUserInfoRQ_m" -> Optional.of(ErkMsgType_e.AddUserInfoRQ);
            case "AddUserInfoRP_m" -> Optional.of(ErkMsgType_e.AddUserInfoRP);
            case "UpdateUserInfoRQ_m" -> Optional.empty();
            case "UpdateUserInfoRP_m" -> Optional.empty();
            case "DelUserInfoRQ_m" -> Optional.of(ErkMsgType_e.DelUserInfoRQ);
            case "DelUserInfoRP_m" -> Optional.of(ErkMsgType_e.DelUserInfoRP);
            case "ErkServiceConnRQ_m" -> Optional.of(ErkMsgType_e.ErkServiceConnRQ);
            case "ErkServiceConnRP_m" -> Optional.of(ErkMsgType_e.ErkServiceConnRP);
            case "ErkServiceDisConnRQ_m" -> Optional.of(ErkMsgType_e.ErkServiceDisConnRQ);
            case "ErkServiceDisConnRP_m" -> Optional.of(ErkMsgType_e.ErkServiceDisConnRP);
            case "EmoServiceStartRQ_m" -> Optional.of(ErkMsgType_e.EmoServiceStartRQ);
            case "EmoServiceStartRP_m" -> Optional.of(ErkMsgType_e.EmoServiceStartRP);
            case "EmoServiceStopRQ_m" -> Optional.of(ErkMsgType_e.EmoServiceStopRQ);
            case "EmoServiceStopRP_m" -> Optional.of(ErkMsgType_e.EmoServiceStopRP);
            case "ErkEngineCreateRQ_m" -> Optional.of(ErkInterMsgType_e.ErkEngineCreateRQ);
            case "ErkEngineCreateRP_m" -> Optional.of(ErkInterMsgType_e.ErkEngineCreateRP);
            case "ErkEngineDeleteRQ_m" -> Optional.of(ErkInterMsgType_e.ErkEngineDeleteRQ);
            case "ErkEngineDeleteRP_m" -> Optional.of(ErkInterMsgType_e.ErkEngineDeleteRP);
            case "HeartBeatRQ_m" -> Optional.of(ErkMsgType_e.HeartBeatRQ);
            case "HeartBeatRP_m" -> Optional.of(ErkMsgType_e.HeartBeatRP);
            case "EmoRecogRQ_m" -> Optional.of(ErkMsgType_e.EmoRecogRQ);
            case "EmoRecogRP_m" -> Optional.of(ErkMsgType_e.EmoRecogRP);
            case "PhysioEmoRecogRQ_m" -> Optional.of(ErkMsgType_e.PhysioEmoRecogRQ);
            case "PhysioEmoRecogRP_m" -> Optional.of(ErkMsgType_e.PhysioEmoRecogRP);
            case "SpeechEmoRecogRQ_m" -> Optional.of(ErkMsgType_e.SpeechEmoRecogRQ);
            case "SpeechEmoRecogRP_m" -> Optional.of(ErkMsgType_e.SpeechEmoRecogRP);
            case "FaceEmoRecogRQ_m" -> Optional.of(ErkMsgType_e.FaceEmoRecogRQ);
            case "FaceEmoRecogRP_m" -> Optional.of(ErkMsgType_e.FaceEmoRecogRP);
            case "EmoRecogNoti_m" -> Optional.empty();
            default -> Optional.empty();
        };
    }
}
