package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.erksystem.protobuf.api.AddServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.AddServiceProviderInfoRQ_m;
import com.erksystem.protobuf.api.ErkMsgType_e;
import com.erksystem.protobuf.api.OrgProfileResult_e;

import static com.erksystem.protobuf.api.OrgProfileResult_e.OrgProfileResult_nok_DB;


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
        provider.setServiceType(msg.getServiceType().getNumber());

        if (!ServiceProviderDAO.create(provider)) {
            throw new RmqHandleException(OrgProfileResult_nok_DB.getNumber(), "FAIL");
        }

        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.AddServiceProviderInfoRP)
                .setQueueInfo(msg.getQueueInfo())
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                .setResultType(OrgProfileResult_e.OrgProfileResult_ok)
                .setOrgId(provider.getOrgId())
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        AddServiceProviderInfoRP_m res = AddServiceProviderInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.AddServiceProviderInfoRP)
                .setQueueInfo(msg.getQueueInfo())
                .setOrgName(msg.getOrgName())
                .setOrgPwd(msg.getOrgPwd())
                .setServiceDuration(msg.getServiceDuration())
                .setUserNumber(msg.getUserNumber())
                .setServiceType(msg.getServiceType())
                .setResultTypeValue(reasonCode)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }
}
