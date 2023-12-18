package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.DelUserInfoRP_m;
import com.erksystem.protobuf.api.DelUserInfoRQ_m;

import static com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
public class DelUserInfoRQHandler extends RmqIncomingHandler<DelUserInfoRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        DelUserInfoRP_m res = DelUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setResultTypeValue(reasonCode)
                .setReturn(reason) // TODO return 값 체크
                .build();

        sendErkApiMsg2API(res);
    }
}
