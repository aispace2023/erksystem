package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
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
        ServiceProvider sp = ServiceProviderDAO.read(msg.getOrgName());
        if (sp == null) {
            throw new RmqHandleException(UserProfileResult_nok_OrgName.getNumber(), "Provider Not Found");
        }

        int result = 0; // unknown error
        ServiceUser su = ServiceUserDAO.read(sp.getOrgId(), msg.getUserName());
        if (su != null && su.getUserPwd().equals(msg.getUserPwd())
                && ServiceUserDAO.delete(sp.getOrgId(), su.getUserId())) {
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
                .setReturn(reason) // TODO return 값 체크
                .build();

        reply(res);
    }
}
