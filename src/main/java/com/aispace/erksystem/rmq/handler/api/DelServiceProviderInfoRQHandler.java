package com.aispace.erksystem.rmq.handler.api;


import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.erksystem.protobuf.api.DelServiceProviderInfoRP_m;
import com.erksystem.protobuf.api.DelServiceProviderInfoRQ_m;
import com.erksystem.protobuf.api.ErkMsgType_e;

import static com.erksystem.protobuf.api.UserProfileResult_e.UserProfileResult_ok;


/**
 * Created by Ai_Space
 */
public class DelServiceProviderInfoRQHandler extends RmqIncomingHandler<DelServiceProviderInfoRQ_m> {
    @Override
    protected void handle() {
        int result = 0; // unknown error

        ServiceProvider sp = ServiceProviderDAO.read(msg.getOrgName());
        if (sp != null && sp.getOrgPwd().equals(msg.getOrgPwd())
                && ServiceProviderDAO.delete(sp.getOrgId())) {
            result = UserProfileResult_ok.getNumber();
        }

        sendRsp(result);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        sendRsp(reasonCode);
    }

    private void sendRsp(int reasonCode) {
        DelServiceProviderInfoRP_m res = DelServiceProviderInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DelServiceProviderInfoRP)
                .setOrgName(msg.getOrgName())
                .setResultTypeValue(reasonCode)
                .build();

        reply(res);
    }
}
