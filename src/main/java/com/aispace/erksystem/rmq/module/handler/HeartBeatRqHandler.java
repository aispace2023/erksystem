package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgHead_s;
import com.erksystem.protobuf.api.HeartBeatRP_m;
import com.erksystem.protobuf.api.HeartBeatRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
public class HeartBeatRqHandler extends RmqIncomingHandler<HeartBeatRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        HeartBeatRP_m res = HeartBeatRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()))
                .setStatus(0)
                .build();

        sendErkApiMsg2API(res);
    }
}
