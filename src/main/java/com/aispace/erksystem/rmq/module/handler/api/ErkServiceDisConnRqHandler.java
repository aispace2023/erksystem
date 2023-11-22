package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class ErkServiceDisConnRqHandler extends RmqIncomingHandler<ErkServiceDisConnRQ_m> {
    /*
    message ErkServiceDisConnRQ{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
      ServiceType_e  ServiceType = 3;
    }

    message ErkServiceDisConnRP{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
      ReturnCode_e ReturnCode = 3;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        ErkServiceDisConnRP_m res = ErkServiceDisConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHeader.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceDisConnRP))
                .setMsgTime(System.currentTimeMillis())
                // .setReturnCode() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
