package com.aispace.erksystem.rmq.module.handler.api;


import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.DelServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.DelServiceProviderInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;


/**
 * Created by Ai_Space
 */
public class DelServiceProviderInfoRQHandler extends RmqIncomingHandler<DelServiceProviderInfoRQ_m> {
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        DelServiceProviderInfoRP_m res = DelServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setResultTypeValue(reasonCode)
                .build();

        sendErkApiMsg2API(res);
    }
}
