package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.erksystem.protobuf.api.*;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;
import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendToApi;

/**
 * Created by Ai_Space
 */
public class ErkServiceConnRqHandler extends RmqIncomingHandler<ErkServiceConnRQ_m> {
    /*
    message ErkServiceConnRQ{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
    }

    message ErkServiceConnRP{
      ErkMsgHeader ErkMsgHead = 1;
      int64 MsgTime = 2;
      ReturnCode_e ReturnCode = 3;
      ServiceType_e ServiceType = 4;
    }
     */
    @Override
    protected void handle() {
        ServiceProvider provider = ServiceProviderDAO.read(msg.getErkMsgHead().getOrgId());
        if (provider == null) {
            throw new IllegalStateException("FAIL");
        }
        ServiceUser user = ServiceUserDAO.read(msg.getErkMsgHead().getUserId(), provider.getOrgId());
        if(user == null) {
            throw new IllegalStateException("FAIL");
        }
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHeader.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCode(ReturnCode_e.ReturnCode_ok)
                .build();

        sendErkApiMsg2API(res);
    }

    @Override
    protected void onFail() {
        ErkServiceConnRP_m res = ErkServiceConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHeader.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceConnRP))
                .setMsgTime(System.currentTimeMillis())
                // .setReturnCode() // TODO
                .build();

        sendErkApiMsg2API(res);
    }
}
