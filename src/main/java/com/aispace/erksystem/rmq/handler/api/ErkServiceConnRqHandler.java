package com.aispace.erksystem.rmq.handler.api;

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
        ServiceUser user = ServiceUserDAO.read(msg.getErkMsgHead().getUserId(), msg.getErkMsgHead().getOrgId());
        if (user == null) {
            throw new IllegalStateException("FAIL");
        }
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCode(ReturnCode_e.ReturnCode_ok)
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
