package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.DelUserInfoRP;
import com.erksystem.protobuf.prov.DelUserInfoRQ;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class DelUserInfoRQHandler extends RmqIncomingHandler<DelUserInfoRQ> {
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
        DelUserInfoRP res = DelUserInfoRP.newBuilder()
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                // .setResultType() // TODO
                // .setReturn() // TODO
                .build();

        sendToApi(res);
    }
}
