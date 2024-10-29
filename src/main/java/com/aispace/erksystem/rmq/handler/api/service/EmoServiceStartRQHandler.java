package com.aispace.erksystem.rmq.handler.api.service;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.module.ErkEngineUtil;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineCreateMsgs;
import static com.erksystem.protobuf.api.ReturnCode_e.*;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
    private ConnectionInfo connectionInfo;

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();
        ServiceType_e serviceType = msg.getServiceType();

        connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        Set<ErkEngineUtil.EngineMsgInfo> engineCreateMsgs = getEngineCreateMsgs(serviceType, orgId, userId);

        if (engineCreateMsgs.isEmpty()) {
            onFail(ReturnCode_unknown.getNumber(), "No Engine Type");
            return;
        }

        connectionInfo.addPromise(
                this::onSuccess,
                () -> onFail(ReturnCode_unknown.getNumber(), "EmoServiceStart Fail"),
                () -> {
                    if (connectionManager.findConnectionInfo(orgId, userId).isPresent()) {
                        onFail(ReturnCode_unknown.getNumber(), "EmoServiceStart Timeout");
                    }
                }, 5000, engineCreateMsgs.size()
        );

        for (ErkEngineUtil.EngineMsgInfo engineCreateMsg : engineCreateMsgs) {
            connectionInfo.declareQueue(engineCreateMsg.getRecvQueue(), engineCreateMsg.getSendQueue());

            RmqOutgoingHandler.send(engineCreateMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineCreateMsg.getEngineType()));
        }
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        // 실패 시 생성한 큐 삭제
        connectionInfo.deleteDeclaredQueues();

        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }

    private void onSuccess() {
        try {
            EmoServiceStartRP_m.Builder emoServiceStartRpBuilder = EmoServiceStartRP_m.newBuilder();

            for (ErkEngineInfo_s engineInfo : connectionInfo.getEngineInfoMap().values()) {
                switch (engineInfo.getEngineType()) {
                    case EngineType_physiology -> emoServiceStartRpBuilder.setPhysioEngineInfo(engineInfo);
                    case EngineType_speech -> emoServiceStartRpBuilder.setSpeechEngineInfo(engineInfo);
                    case EngineType_face -> emoServiceStartRpBuilder.setFaceEngineInfo(engineInfo);
                    case EngineType_knowledge -> emoServiceStartRpBuilder.setKnowledgeEngineInfo(engineInfo);
                }
            }

            // Response 전송
            EmoServiceStartRP_m res = emoServiceStartRpBuilder
                    .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                    .setMsgTime(System.currentTimeMillis())
                    .setReturnCode(ReturnCode_ok)
                    .build();
            reply(res);
        } catch (Exception e) {
            log.warn("Unexpected Exception Occurs", e);
            onFail(ReturnCode_unknown.getNumber(), "Unexpected Exception");
        }
    }
}
