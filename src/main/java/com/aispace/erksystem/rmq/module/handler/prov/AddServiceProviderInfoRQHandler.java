package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.AddServiceProviderInfoRP;
import com.erksystem.protobuf.prov.AddServiceProviderInfoRQ;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class AddServiceProviderInfoRQHandler extends RmqIncomingHandler<AddServiceProviderInfoRQ> {
    /*
    message AddServiceProviderInfoRQ{
      string OrgName = 1;
      string OrgPwd = 2;
      string ServiceDuration = 3;
      int32 UserNumber = 4;
      ServiceType_e ServiceType = 5;
    }

    message AddServiceProviderInfoRP{
      string OrgName = 1;
      OrgProfileResult_e ResultType = 2;
      int32 OrgId = 3;
      string OrgPwd = 4;
      string ServiceDuration = 5;
      int32 UserNumber = 6;
      ServiceType_e ServiceType = 7;
    }

     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        AddServiceProviderInfoRP res = AddServiceProviderInfoRP.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                // .setResultType() // TODO
                // .setOrgId() // TODO
                .build();

        sendToApi(res);
    }
}
