package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.prov.UpdateServiceProviderInfoRP_m;
import com.erksystem.protobuf.prov.UpdateServiceProviderInfoRQ_m;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkProvMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class UpdateServiceProviderInfoRQHandler extends RmqIncomingHandler<UpdateServiceProviderInfoRQ_m> {
    /*
    message UpdateServiceProviderInfoRQ{
      string OrgName = 1;
      string Old_OrgPwd = 2;
      string Old_ServiceDuration = 3;
      int32 Old_UserNumber = 4;
      ServiceType_e Old_ServiceType = 5;
      string New_OrgPwd = 6;
      string New_ServiceDuration = 7;
      int32 New_UserNumber = 8;
      ServiceType_e New_ServiceType = 9;
    }

    message UpdateServiceProviderInfoRP{
      string OrgName = 1;
      OrgProfileResult_e ResultType = 2;
      string Old_OrgPwd = 3;
      string Old_ServiceDuration = 4;
      int32 Old_UserNumber = 5;
      ServiceType_e Old_ServiceType = 6;
      string New_OrgPwd = 7;
      string New_ServiceDuration = 8;
      int32 New_UserNumber = 9;
      ServiceType_e New_ServiceType = 10;
    }
     */
    @Override
    protected void handle() {
        throw new IllegalStateException("FAIL");
        // TODO 개발 필요
    }

    @Override
    protected void onFail() {
        UpdateServiceProviderInfoRP_m res = UpdateServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOldOrgPwd(msg.getOldOrgPwd())
                .setOldServiceDuration(msg.getOldServiceDuration())
                .setOldUserNumber(msg.getOldUserNumber())
                .setOldServiceType(msg.getOldServiceType())
                .setNewOrgPwd(msg.getNewOrgPwd())
                .setNewServiceDuration(msg.getNewServiceDuration())
                .setNewUserNumber(msg.getNewUserNumber())
                .setNewServiceType(msg.getNewServiceType())
                // .setResultType() // TODO
                .build();

        sendErkProvMsg2API(res);
    }
}
