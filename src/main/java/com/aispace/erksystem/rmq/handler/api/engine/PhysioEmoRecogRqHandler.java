package com.aispace.erksystem.rmq.handler.api.engine;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgDataHead_s;
import com.erksystem.protobuf.api.PhysioEmoRecogRP_m;
import com.erksystem.protobuf.api.PhysioEmoRecogRQ_m;

/**
 * Created by Ai_Space
 */
public class PhysioEmoRecogRqHandler extends RmqIncomingHandler<PhysioEmoRecogRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        PhysioEmoRecogRP_m res = PhysioEmoRecogRP_m.newBuilder()
                .setErkMsgDataHead(ErkMsgDataHead_s.newBuilder(msg.getErkMsgDataHead()))
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
