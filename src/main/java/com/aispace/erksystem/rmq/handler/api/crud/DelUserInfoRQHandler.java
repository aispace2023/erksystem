package com.aispace.erksystem.rmq.handler.api.crud;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.erksystem.protobuf.api.DelUserInfoRP_m;
import com.erksystem.protobuf.api.DelUserInfoRQ_m;
import com.erksystem.protobuf.api.ErkMsgType_e;

import static com.erksystem.protobuf.api.UserProfileResult_e.UserProfileResult_nok_OrgName;
import static com.erksystem.protobuf.api.UserProfileResult_e.UserProfileResult_ok;

/**
 * Created by Ai_Space
 */
public class DelUserInfoRQHandler extends RmqIncomingHandler<DelUserInfoRQ_m> {
    @Override
    protected void handle() {
        // User Delete 시 필요한 값은 아니나, 조직명이 유효한 지 확인한다.
        ServiceProvider sp = ServiceProviderDAO.read(msg.getOrgName());
        if (sp == null) {
            throw new RmqHandleException(UserProfileResult_nok_OrgName.getNumber(), "Provider Not Found");
        }

        int result = 0; // unknown error
        if (ServiceUserDAO.delete(msg.getUserName())) {
            result = UserProfileResult_ok.getNumber();
        }

        sendRsp(result, "Success");
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        sendRsp(reasonCode, reason);
    }

    private void sendRsp(int reasonCode, String reason) {
        DelUserInfoRP_m res = DelUserInfoRP_m.newBuilder()
                .setMsgType(ErkMsgType_e.DelUserInfoRP)
                .setOrgName(msg.getOrgName())
                .setUserName(msg.getUserName())
                .setResultTypeValue(reasonCode)
                .setReturn(reason)
                .build();

        reply(res);
    }
}
