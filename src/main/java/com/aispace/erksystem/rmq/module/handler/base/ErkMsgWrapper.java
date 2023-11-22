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
            builder.setErkServiceConnRQ(casted);
        } else if (msg instanceof ErkServiceConnRP_m casted) {
            builder.setErkServiceConnRP(casted);
        } else if (msg instanceof ErkServiceDisConnRQ_m casted) {
            builder.setErkServiceDisConnRQ(casted);
        } else if (msg instanceof ErkServiceDisConnRP_m casted) {
            builder.setErkServiceDisConnRP(casted);
        } else if (msg instanceof EmoRecogCreateRQ_m casted) {
            builder.setEmoRecogCreateRQ(casted);
        } else if (msg instanceof EmoRecogCreateRP_m casted) {
            builder.setEmoRecogCreateRP(casted);
        } else if (msg instanceof EmoRecogDeleteRQ_m casted) {
            builder.setEmoRecogDeleteRQ(casted);
        } else if (msg instanceof EmoRecogDeleteRP_m casted) {
            builder.setEmoRecogDeleteRP(casted);
        } else if (msg instanceof HeartBeatRQ_m casted) {
            builder.setHeartBeatRQ(casted);
        } else if (msg instanceof HeartBeatRP_m casted) {
            builder.setHeartBeatRP(casted);
        } else if (msg instanceof EmoRecogRQ_m casted) {
            builder.setEmoRecogRQ(casted);
        } else if (msg instanceof EmoRecogRP_m casted) {
            builder.setEmoRecogRP(casted);
        } else if (msg instanceof PhysioEmoRecogRQ_m casted) {
            builder.setPhysioEmoRecogRQ(casted);
        } else if (msg instanceof PhysioEmoRecogRP_m casted) {
            builder.setPhysioEmoRecogRP(casted);
        } else if (msg instanceof SpeechEmoRecogRQ_m casted) {
            builder.setSpeechEmoRecogRQ(casted);
        } else if (msg instanceof SpeechEmoRecogRP_m casted) {
            builder.setSpeechEmoRecogRP(casted);
        } else if (msg instanceof FaceEmoRecogRQ_m casted) {
            builder.setFaceEmoRecogRQ(casted);
        } else if (msg instanceof FaceEmoRecogRP_m casted) {
            builder.setFaceEmoRecogRP(casted);
        } else if (msg instanceof EmoRecogNoti_m casted) {
            builder.setEmoRecogNoti(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }

    public static ErkProvMsg_m wrap2ErkProvMsg(Message msg) {
        ErkProvMsg_m.Builder builder = ErkProvMsg_m.newBuilder();
        if (msg instanceof AddServiceProviderInfoRQ_m casted) {
            builder.setAddServiceProviderInfoRQ(casted);
        } else if (msg instanceof AddServiceProviderInfoRP_m casted) {
            builder.setAddServiceProviderInfoRP(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRQ_m casted) {
            builder.setUpdateServiceProviderInfoRQ(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRP_m casted) {
            builder.setUpdateServiceProviderInfoRP(casted);
        } else if (msg instanceof DelServiceProviderInfoRQ_m casted) {
            builder.setDelServiceProviderInfoRQ(casted);
        } else if (msg instanceof DelServiceProviderInfoRP_m casted) {
            builder.setDelServiceProviderInfoRP(casted);
        } else if (msg instanceof AddUserInfoRQ_m casted) {
            builder.setAddUserInfoRQ(casted);
        } else if (msg instanceof AddUserInfoRP_m casted) {
            builder.setAddUserInfoRP(casted);
        } else if (msg instanceof UpdateUserInfoRQ_m casted) {
            builder.setUpdateUserInfoRQ(casted);
        } else if (msg instanceof UpdateUserInfoRP_m casted) {
            builder.setUpdateUserInfoRP(casted);
        } else if (msg instanceof DelUserInfoRQ_m casted) {
            builder.setDelUserInfoRQ(casted);
        } else if (msg instanceof DelUserInfoRP_m casted) {
            builder.setDelUserInfoRP(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }
}
