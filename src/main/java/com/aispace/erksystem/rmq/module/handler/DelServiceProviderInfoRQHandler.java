package com.aispace.erksystem.rmq.module.handler;


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
    protected void onFail() {
        DelServiceProviderInfoRP_m res = DelServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                // .setResultType() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
