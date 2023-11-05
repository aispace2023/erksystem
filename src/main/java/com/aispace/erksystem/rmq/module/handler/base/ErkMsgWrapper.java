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
        if (msg instanceof ErkServiceConnRQ casted) {
            builder.setErkServiceConnRq(casted);
        } else if (msg instanceof ErkServiceConnRP casted) {
            builder.setErkServiceConnRp(casted);
        } else if (msg instanceof ErkServiceDisConnRQ casted) {
            builder.setErkServiceDisConnRq(casted);
        } else if (msg instanceof ErkServiceDisConnRP casted) {
            builder.setErkServiceDisConnRp(casted);
        } else if (msg instanceof EmoRecogCreateRQ casted) {
            builder.setEmoRecogCreateRq(casted);
        } else if (msg instanceof EmoRecogCreateRP casted) {
            builder.setEmoRecogCreateRp(casted);
        } else if (msg instanceof EmoRecogDeleteRQ casted) {
            builder.setEmoRecogDeleteRq(casted);
        } else if (msg instanceof EmoRecogDeleteRP casted) {
            builder.setEmoRecogDeleteRp(casted);
        } else if (msg instanceof HB_RQ casted) {
            builder.setHbRq(casted);
        } else if (msg instanceof HB_RP casted) {
            builder.setHbRp(casted);
        } else if (msg instanceof EmoRecogRQ casted) {
            builder.setEmoRecogRq(casted);
        } else if (msg instanceof EmoRecogRP casted) {
            builder.setEmoRecogRp(casted);
        } else if (msg instanceof PhysioEmoRecogRQ casted) {
            builder.setPhysioEmoRecogRq(casted);
        } else if (msg instanceof PhysioEmoRecogRP casted) {
            builder.setPhysioEmoRecogRp(casted);
        } else if (msg instanceof SpeechEmoRecogRQ casted) {
            builder.setSpeechEmoRecogRq(casted);
        } else if (msg instanceof SpeechEmoRecogRP casted) {
            builder.setSpeechEmoRecogRp(casted);
        } else if (msg instanceof FaceEmoRecogRQ casted) {
            builder.setFaceEmoRecogRq(casted);
        } else if (msg instanceof FaceEmoRecogRP casted) {
            builder.setFaceEmoRecogRp(casted);
        } else if (msg instanceof EmoRecogNoti casted) {
            builder.setEmoRecogNoti(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }

    public static ErkProvMsg wrap2ErkProvMsg(Message msg) {
        ErkProvMsg.Builder builder = ErkProvMsg.newBuilder();
        if (msg instanceof AddServiceProviderInfoRQ casted) {
            builder.setAddServiceProviderInfoRq(casted);
        } else if (msg instanceof AddServiceProviderInfoRP casted) {
            builder.setAddServiceProviderInfoRp(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRQ casted) {
            builder.setUpdateServiceProviderInfoRq(casted);
        } else if (msg instanceof UpdateServiceProviderInfoRP casted) {
            builder.setUpdateServiceProviderInfoRp(casted);
        } else if (msg instanceof DelServiceProviderInfoRQ casted) {
            builder.setDelServiceProviderInfoRq(casted);
        } else if (msg instanceof DelServiceProviderInfoRP casted) {
            builder.setDelServiceProviderInfoRp(casted);
        } else if (msg instanceof AddUserInfoRQ casted) {
            builder.setAddUserInfoRq(casted);
        } else if (msg instanceof AddUserInfoRP casted) {
            builder.setAddUserInfoRp(casted);
        } else if (msg instanceof UpdateUserInfoRQ casted) {
            builder.setUpdateUserInfoRq(casted);
        } else if (msg instanceof UpdateUserInfoRP casted) {
            builder.setUpdateUserInfoRp(casted);
        } else if (msg instanceof DelUserInfoRQ casted) {
            builder.setDelUserInfoRq(casted);
        } else if (msg instanceof DelUserInfoRP casted) {
            builder.setDelUserInfoRp(casted);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + msg.getClass());
        }
        return builder.build();
    }
}
