package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.erksystem.protobuf.api.ErkMsgHead_s;
import com.erksystem.protobuf.api.HeartBeatRP_m;
import com.erksystem.protobuf.api.HeartBeatRQ_m;

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
    protected void onFail(int reasonCode, String reason) {
        HeartBeatRP_m res = HeartBeatRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()))
                .setStatus(0)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }
}
