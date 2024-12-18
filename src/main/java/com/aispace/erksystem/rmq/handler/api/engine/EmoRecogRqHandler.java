package com.aispace.erksystem.rmq.handler.api.engine;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.EmoRecogRP_m;
import com.erksystem.protobuf.api.EmoRecogRQ_m;
import com.erksystem.protobuf.api.ErkMsgDataHead_s;

/**
 * Created by Ai_Space
 */
public class EmoRecogRqHandler extends RmqIncomingHandler<EmoRecogRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        EmoRecogRP_m res = EmoRecogRP_m.newBuilder()
                .setErkMsgDataHead(ErkMsgDataHead_s.newBuilder(msg.getErkMsgDataHead()))
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
