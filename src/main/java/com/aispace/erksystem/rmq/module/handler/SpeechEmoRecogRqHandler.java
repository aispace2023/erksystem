package com.aispace.erksystem.rmq.module.handler;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.ErkMsgDataHead_s;
import com.erksystem.protobuf.api.SpeechEmoRecogRP_m;
import com.erksystem.protobuf.api.SpeechEmoRecogRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

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
    protected void onFail() {
        SpeechEmoRecogRP_m res = SpeechEmoRecogRP_m.newBuilder()
                .setErkMsgDataHead(ErkMsgDataHead_s.newBuilder(msg.getErkMsgDataHead()))
                // .setReturnCode() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
