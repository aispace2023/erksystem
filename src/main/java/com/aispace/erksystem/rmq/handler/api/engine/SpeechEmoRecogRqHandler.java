package com.aispace.erksystem.rmq.handler.api.engine;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgDataHead_s;
import com.erksystem.protobuf.api.SpeechEmoRecogRP_m;
import com.erksystem.protobuf.api.SpeechEmoRecogRQ_m;

/**
 * Created by Ai_Space
 */
public class SpeechEmoRecogRqHandler extends RmqIncomingHandler<SpeechEmoRecogRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        SpeechEmoRecogRP_m res = SpeechEmoRecogRP_m.newBuilder()
                .setErkMsgDataHead(ErkMsgDataHead_s.newBuilder(msg.getErkMsgDataHead()))
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
