package com.aispace.erksystem.rmq.handler.api.connection;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.erksystem.protobuf.api.*;

/**
 * Created by Ai_Space
 */
public class ErkServiceConnRqHandler extends RmqIncomingHandler<ErkServiceConnRQ_m> {
    @Override
    protected void handle() {
        // SERVICE_USER 가 SERVICE_PROVIDER 를 참조하므로 SERVICE_USER_TBL 만 조회해도 유효하다.
        int userId = msg.getErkMsgHead().getUserId();
        int orgId = msg.getErkMsgHead().getOrgId();

        ServiceUser user = ServiceUserDAO.read(userId, orgId);
        if (user == null) {
            throw new IllegalStateException("FAIL");
        }

        connectionManager.createConnection(orgId, userId);

        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCode(ReturnCode_e.ReturnCode_ok)
                .setServiceType(ServiceType_e.forNumber(user.getServiceType()))
                .build();

        reply(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
