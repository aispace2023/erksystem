package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgHead_s;
import com.erksystem.protobuf.api.ErkMsgType_e;
import com.erksystem.protobuf.api.ErkServiceDisConnRP_m;
import com.erksystem.protobuf.api.ErkServiceDisConnRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
public class ErkServiceDisConnRqHandler extends RmqIncomingHandler<ErkServiceDisConnRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        ErkServiceDisConnRP_m res = ErkServiceDisConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceDisConnRP))
                .setMsgTime(System.currentTimeMillis())
                // .setReturnCode() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
