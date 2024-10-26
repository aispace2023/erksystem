package com.aispace.erksystem.rmq.handler.api.connection;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.module.ErkEngineUtil;
import com.erksystem.protobuf.api.*;

import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineDeleteMsgs;

/**
 * Created by Ai_Space
 */
public class ErkServiceDisConnRqHandler extends RmqIncomingHandler<ErkServiceDisConnRQ_m> {

    @Override
    protected void handle() {
        int userId = msg.getErkMsgHead().getUserId();
        int orgId = msg.getErkMsgHead().getOrgId();
        ServiceType_e serviceType = msg.getServiceType();
        ConnectionInfo connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        // Send EngineDeleteMsg
        for (ErkEngineUtil.EngineMsgInfo engineDeleteMsg : getEngineDeleteMsgs(serviceType, orgId, userId)) {
            RmqOutgoingHandler.send(engineDeleteMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineDeleteMsg.getEngineType()));
        }

        connectionManager.deleteConnection(connectionInfo.getKey());

        reply(ErkServiceDisConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceDisConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCode(ReturnCode_e.ReturnCode_ok)
                .build());
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        ErkServiceDisConnRP_m res = ErkServiceDisConnRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.ErkServiceDisConnRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
