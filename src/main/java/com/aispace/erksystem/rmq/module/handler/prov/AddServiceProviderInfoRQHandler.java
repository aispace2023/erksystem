package com.aispace.erksystem.rmq.module.handler.prov;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.type.ServiceType;
import com.erksystem.protobuf.prov.AddServiceProviderInfoRP_m;
import com.erksystem.protobuf.prov.AddServiceProviderInfoRQ_m;
import com.erksystem.protobuf.prov.OrgProfileResult_e;
import com.erksystem.protobuf.prov.ServiceType_e;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.*;

/**
 * Created by Ai_Space
 */
public class AddServiceProviderInfoRQHandler extends RmqIncomingHandler<AddServiceProviderInfoRQ_m> {
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
        ServiceProvider provider = new ServiceProvider();
        provider.setOrgName(msg.getOrgName());
        provider.setOrgPwd(msg.getOrgPwd());
        provider.setServiceDuration(msg.getServiceDuration());
        provider.setUserNumber(msg.getUserNumber());
        provider.setServiceType(ServiceType.speech);    // TODO enum mapping

        long orgId = ServiceProviderDAO.create(provider);
        if (orgId == 0) {
            throw new IllegalStateException("FAIL");
        }

        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setOrgId((int)orgId)  // TODO check long -> int
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                .setResultType(OrgProfileResult_e.OrgProfileResult_ok)
                .build();

        sendErkProvMsg2API(res);
    }

    @Override
    protected void onFail() {
        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                // .setResultType() // TODO
                // .setOrgId() // TODO
                .build();

        sendErkProvMsg2API(res);
    }
}
