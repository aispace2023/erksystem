package com.aispace.erksystem.rmq.module.handler.base;

import com.erksystem.protobuf.api.*;
import com.erksystem.protobuf.prov.*;
import com.google.protobuf.Message;

/**
 * Created by Ai_Space
 */
public class ErkMsgWrapper {
    private ErkMsgWrapper() {
    }

    public static ErkApiMsg wrap2ErkApiMsg(Message msg) {
        ErkApiMsg.Builder builder = ErkApiMsg.newBuilder();
        if (msg instanceof ErkServiceConnRQ_m casted) {
            builder.setErkServiceConnRq(casted);
        } else if (msg instanceof ErkServiceConnRP_m casted) {
            builder.setErkServiceConnRp(casted);
        } else if (msg instanceof ErkServiceDisConnRQ_m casted) {
            builder.setErkServiceDisConnRq(casted);
        } else if (msg instanceof ErkServiceDisConnRP_m casted) {
            builder.setErkServiceDisConnRp(casted);
        } else if (msg instanceof EmoRecogCreateRQ_m casted) {
            builder.setEmoRecogCreateRq(casted);
        } else if (msg instanceof EmoRecogCreateRP_m casted) {
            builder.setEmoRecogCreateRp(casted);
        } else if (msg instanceof EmoRecogDeleteRQ_m casted) {
            builder.setEmoRecogDeleteRq(casted);
        } else if (msg instanceof EmoRecogDeleteRP_m casted) {
            builder.setEmoRecogDeleteRp(casted);
        } else if (msg instanceof HeartBeatRQ_m casted) {
            builder.setHeartBeatRQ(casted);
        } else if (msg instanceof HeartBeatRP_m casted) {
            builder.setHeartBeatRP(casted);
        } else if (msg instanceof EmoRecogRQ_m casted) {
            builder.setEmoRecogRq(casted);
        } else if (msg instanceof EmoRecogRP_m casted) {
            builder.setEmoRecogRp(casted);
        } else if (msg instanceof PhysioEmoRecogRQ_m casted) {
            builder.setPhysioEmoRecogRq(casted);
        } else if (msg instanceof PhysioEmoRecogRP_m casted) {
            builder.setPhysioEmoRecogRp(casted);
        } else if (msg instanceof SpeechEmoRecogRQ_m casted) {
            builder.setSpeechEmoRecogRq(casted);
        } else if (msg instanceof SpeechEmoRecogRP_m casted) {
            builder.setSpeechEmoRecogRp(casted);
        } else if (msg instanceof FaceEmoRecogRQ_m casted) {
            builder.setFaceEmoRecogRq(casted);
        } else if (msg instanceof FaceEmoRecogRP_m casted) {
            builder.setFaceEmoRecogRp(casted);
        } else if (msg instanceof EmoRecogNoti_m casted) {
            builder.setEmoRecogNoti(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }

    public static ErkProvMsg wrap2ErkProvMsg(Message msg) {
        ErkProvMsg.Builder builder = ErkProvMsg.newBuilder();
        if (msg instanceof AddServiceProviderInfoRQ_m casted) {
            builder.setAddServiceProviderInfoRq(casted);
        } else if (msg instanceof AddServiceProviderInfoRP_m casted) {
            builder.setAddServiceProviderInfoRp(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRQ_m casted) {
            builder.setUpdateServiceProviderInfoRq(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRP_m casted) {
            builder.setUpdateServiceProviderInfoRp(casted);
        } else if (msg instanceof DelServiceProviderInfoRQ_m casted) {
            builder.setDelServiceProviderInfoRq(casted);
        } else if (msg instanceof DelServiceProviderInfoRP_m casted) {
            builder.setDelServiceProviderInfoRp(casted);
        } else if (msg instanceof AddUserInfoRQ_m casted) {
            builder.setAddUserInfoRq(casted);
        } else if (msg instanceof AddUserInfoRP_m casted) {
            builder.setAddUserInfoRp(casted);
        } else if (msg instanceof UpdateUserInfoRQ_m casted) {
            builder.setUpdateUserInfoRq(casted);
        } else if (msg instanceof UpdateUserInfoRP_m casted) {
            builder.setUpdateUserInfoRp(casted);
        } else if (msg instanceof DelUserInfoRQ_m casted) {
            builder.setDelUserInfoRq(casted);
        } else if (msg instanceof DelUserInfoRP_m casted) {
            builder.setDelUserInfoRp(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }
}
