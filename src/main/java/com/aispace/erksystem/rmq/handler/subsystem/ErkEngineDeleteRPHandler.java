package com.aispace.erksystem.rmq.handler.subsystem;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.EngineType_e;
import com.erksystem.protobuf.api.ErkEngineDeleteRP_m;
import com.erksystem.protobuf.api.ErkInterMsgHead_s;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkEngineDeleteRPHandler extends RmqIncomingHandler<ErkEngineDeleteRP_m> {
    @Override
    protected void handle() {
        ErkInterMsgHead_s erkMsgHead = msg.getErkInterMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();

        ConnectionInfo connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        if (msg.getPhysioEngineInfo().getEngineType() == EngineType_e.EngineType_physiology) {
            connectionInfo.getEngineInfoMap().remove(EngineType_e.EngineType_physiology);
        } else if (msg.getSpeechEngineInfo().getEngineType() == EngineType_e.EngineType_speech) {
            connectionInfo.getEngineInfoMap().remove(EngineType_e.EngineType_speech);
        } else if (msg.getFaceEngineInfo().getEngineType() == EngineType_e.EngineType_face) {
            connectionInfo.getEngineInfoMap().remove(EngineType_e.EngineType_face);
        } else if (msg.getKnowledgeEngineInfo().getEngineType() == EngineType_e.EngineType_knowledge) {
            connectionInfo.getEngineInfoMap().remove(EngineType_e.EngineType_knowledge);
        } else {
            log.error("Unknown Engine Type");
        }

        connectionInfo.countDownPromise();
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        // Do Nothing
    }
}
