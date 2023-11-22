package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgDataHeader;
import com.erksystem.protobuf.api.FaceEmoRecogRP_m;
import com.erksystem.protobuf.api.FaceEmoRecogRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class FaceEmoRecogRqHandler extends RmqIncomingHandler<FaceEmoRecogRQ_m> {
    /*
    message FaceEmoRecogRQ{
      ErkMsgDataHeader ErkMsgDataHead = 1;
      int64 DataTimeStamp = 2;
      int32 MsgDataLength = 3;
      bytes MsgDataFrame = 4;
    }

    message FaceEmoRecogRP{
      ErkMsgDataHeader ErkMsgDataHead = 1;
      ReturnCode_e ReturnCode = 2;
      int64 EmoRecogTime = 3;
      EmotionType_e Emotion = 4;
      float Accuracy = 5;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        FaceEmoRecogRP_m res = FaceEmoRecogRP_m.newBuilder()
                .setErkMsgDataHead(ErkMsgDataHeader.newBuilder(msg.getErkMsgDataHead()))
                // .setReturnCode() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}