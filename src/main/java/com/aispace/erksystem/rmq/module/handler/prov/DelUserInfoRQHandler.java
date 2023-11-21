package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.DelUserInfoRP_m;
import com.erksystem.protobuf.prov.DelUserInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class DelUserInfoRQHandler extends RmqIncomingHandler<DelUserInfoRQ_m> {
    /*
    message DelUserInfoRQ{
      string OrgName = 1;
      string UserName = 2;
      string UserPwd = 3;
    }

    message DelUserInfoRP{
      string OrgName = 1;
      string UserName = 2;
      UserProfileResult_e ResultType = 3;
      string Return = 4;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        DelUserInfoRP_m res = DelUserInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                // .setResultType() // TODO
                // .setReturn() // TODO
                .build();

        sendErkProvMsg2API(res);
    }
}
