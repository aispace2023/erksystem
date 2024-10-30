package com.aispace.erksystem.rmq.handler.api.crud;


import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.erksystem.protobuf.api.*;


/**
 * Created by Ai_Space
 */
public class DisServiceProviderInfoRQHandler extends RmqIncomingHandler<DisServiceProviderInfoRQ_m> {
    @Override
    protected void handle() {
        ServiceProvider sp = ServiceProviderDAO.read(msg.getOrgName());

        if (sp == null) {
            throw new RmqHandleException(OrgProfileResult_e.OrgProfileResult_nok_OrgName.getNumber(), "Org not found");
        } else if (!sp.getOrgPwd().equals(msg.getOrgPwd())) {
            throw new RmqHandleException(OrgProfileResult_e.OrgProfileResult_nok_OrgPwd.getNumber(), "Invalid Info");
        }

        DisServiceProviderInfoRP_m res = DisServiceProviderInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DisServiceProviderInfoRP)
                .setResultType(OrgProfileResult_e.OrgProfileResult_ok)
                .setOrgId(sp.getOrgId())
                .setOrgName(sp.getOrgName())
                .setOrgPwd(sp.getOrgPwd())
                .setProviderTypeValue(sp.getProviderType())
                .setServiceDuration(sp.getServiceDuration())
                .setUserNumber(sp.getUserNumber())
                .setServiceTypeValue(sp.getServiceType())
                .build();

        reply(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        reply(DisServiceProviderInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DisServiceProviderInfoRP)
                .setOrgName(msg.getOrgName())
                .setResultTypeValue(reasonCode)
                .build());
    }
}
