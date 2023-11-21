package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
public class HeartBeatRqHandler extends RmqIncomingHandler<HeartBeatRQ_m> {
    /*
    message HB_RQ{
      ErkMsgHeader ErkMsgHead = 1;
      string Queue_id = 2;
    }

    message HB_RP{
      ErkMsgHeader ErkMsgHead = 1;
      int32 Status = 2;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        HeartBeatRP_m res = HeartBeatRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHeader.newBuilder(msg.getErkMsgHead()))
                .setStatus(0)
                .build();

        sendErkApiMsg2API(res);
    }
}
