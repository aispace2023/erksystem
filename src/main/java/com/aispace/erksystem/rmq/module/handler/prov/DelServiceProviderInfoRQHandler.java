package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.DelServiceProviderInfoRP_m;
import com.erksystem.protobuf.prov.DelServiceProviderInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class DelServiceProviderInfoRQHandler extends RmqIncomingHandler<DelServiceProviderInfoRQ_m> {
    /*
    message DelServiceProviderInfoRQ{
      string OrgName = 1;
      string OrgPwd = 2;
    }
    message DelServiceProviderInfoRP{
      string OrgName = 1;
      OrgProfileResult_e ResultType = 2;
    }
     */
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

        sendErkProvMsg2API(res);
    }
}
