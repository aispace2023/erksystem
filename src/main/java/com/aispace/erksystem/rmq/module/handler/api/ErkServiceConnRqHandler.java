package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class ErkServiceConnRqHandler extends RmqIncomingHandler<ErkServiceConnRQ> {
    /*
    message ErkServiceConnRQ{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
    }

    message ErkServiceConnRP{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
      ReturnCode_e ReturnCode = 3;
      ServiceType_e ServiceType = 4;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        ErkServiceConnRP res = ErkServiceConnRP.newBuilder()
                .setErkMsgHead(ErkMsgHeader.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.erkServiceConnRp))
                .setMsgTime(System.currentTimeMillis())
                // .setReturnCode() // TODO
                .build();

        sendToApi(res);
    }
}
