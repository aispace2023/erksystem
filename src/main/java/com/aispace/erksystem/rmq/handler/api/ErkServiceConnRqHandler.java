package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.erksystem.protobuf.api.*;

/**
 * Created by Ai_Space
 */
public class ErkServiceConnRqHandler extends RmqIncomingHandler<ErkServiceConnRQ_m> {
    @Override
    protected void handle() {
        ServiceProvider provider = ServiceProviderDAO.read(msg.getErkMsgHead().getOrgId());
        if (provider == null) {
            throw new IllegalStateException("FAIL");
        }
        ServiceUser user = ServiceUserDAO.read(msg.getErkMsgHead().getUserId(), provider.getOrgId());
        if (user == null) {
            throw new IllegalStateException("FAIL");
        }
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCode(ReturnCode_e.ReturnCode_ok)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }
}
