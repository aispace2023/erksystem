package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.type.ServiceType;
import com.erksystem.protobuf.api.AddServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.AddServiceProviderInfoRQ_m;
import com.erksystem.protobuf.api.OrgProfileResult_e;


/**
 * Created by Ai_Space
 */
public class AddServiceProviderInfoRQHandler extends RmqIncomingHandler<AddServiceProviderInfoRQ_m> {
    @Override
    protected void handle() {
        ServiceProvider provider = new ServiceProvider();
        provider.setOrgName(msg.getOrgName());
        provider.setOrgPwd(msg.getOrgPwd());
        provider.setServiceDuration(msg.getServiceDuration());
        provider.setUserNumber(msg.getUserNumber());
        provider.setServiceType(ServiceType.getName(msg.getServiceType().getNumber()));

        if (!ServiceProviderDAO.create(provider)) {
            throw new IllegalStateException("FAIL");
        }

        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setOrgId(provider.getOrgId())
                .setOrgName(provider.getOrgName())
                .setOrgPwd(provider.getOrgPwd())
                .setServiceDuration(provider.getServiceDuration())
                .setUserNumber(provider.getUserNumber())
                .setServiceType(msg.getServiceType())
                .setResultType(OrgProfileResult_e.OrgProfileResult_ok)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                .setResultTypeValue(reasonCode)
                // .setOrgId() // TODO
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }
}
