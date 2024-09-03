package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.module.ErkEngineUtil;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineCreateMsgs;
import static com.erksystem.protobuf.api.ReturnCode_e.*;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
    private final RmqModule rmqModule = rmqManager.getRmqModule();
    private ConnectionInfo connectionInfo;

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();
        ServiceType_e serviceType = msg.getServiceType();

        connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        Set<ErkEngineUtil.EngineMsgInfo> engineCreateMsgs = getEngineCreateMsgs(serviceType, orgId, userId);

        for (ErkEngineUtil.EngineMsgInfo engineCreateMsg : engineCreateMsgs) {
            queueDeclare(engineCreateMsg.getRecvQueue());
            queueDeclare(engineCreateMsg.getSendQueue());

            RmqOutgoingHandler.send(engineCreateMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineCreateMsg.getEngineType()));
        }

        connectionInfo.addPromise(
                () -> {
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
                },
                () -> onFail(ReturnCode_unknown.getNumber(), "EmoServiceStart Fail"),
                () -> {
                    if (connectionManager.findConnectionInfo(orgId, userId).isPresent()) {
                        onFail(ReturnCode_unknown.getNumber(), "EmoServiceStart Timeout");
                    }
                }, 5000, engineCreateMsgs.size()
        );
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        // 실패 시 생성한 큐 삭제
        if (connectionInfo != null) {
            for (String declaredQueue : connectionInfo.getDeclaredQueues()) {
                try {
                    rmqModule.getChannel().queueDelete(declaredQueue);
                } catch (Exception e) {
                    // Do Nothing
                }
            }
        }
        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }

    private void queueDeclare(String queueName) {
        try {
            rmqModule.getChannel().queueDeclare(queueName, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
            connectionInfo.getDeclaredQueues().add(queueName);
            log.info("Queue Declared [{}]", queueName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
