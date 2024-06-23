package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.module.ErkEngineUtil;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineDeleteMsgs;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStopRQHandler extends RmqIncomingHandler<EmoServiceStopRQ_m> {
    private final RmqModule rmqModule = rmqManager.getRmqModule();
    private ConnectionInfo connectionInfo;

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();
        ServiceType_e serviceType = msg.getServiceType();

        connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        Set<ErkEngineUtil.EngineMsgInfo> engineDeleteMsgs = getEngineDeleteMsgs(serviceType, orgId, userId);

        for (ErkEngineUtil.EngineMsgInfo engineDeleteMsg : engineDeleteMsgs) {
            deleteQueue(engineDeleteMsg.getRecvQueue());
            deleteQueue(engineDeleteMsg.getSendQueue());

            RmqOutgoingHandler.send(engineDeleteMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineDeleteMsg.getEngineType()));
        }

        connectionInfo.addPromise(
                () -> {
                    try {
                        EmoServiceStopRP_m.Builder emoServiceStopRpBuilder = EmoServiceStopRP_m.newBuilder();

                        for (ErkEngineInfo_s engineInfo : connectionInfo.getEngineInfoMap().values()) {
                            switch (engineInfo.getEngineType()) {
                                case EngineType_physiology -> emoServiceStopRpBuilder.setPhysioEngineInfo(engineInfo);
                                case EngineType_speech -> emoServiceStopRpBuilder.setSpeechEngineInfo(engineInfo);
                                case EngineType_face -> emoServiceStopRpBuilder.setFaceEngineInfo(engineInfo);
                                case EngineType_knowledge -> emoServiceStopRpBuilder.setKnowledgeEngineInfo(engineInfo);
                            }
                        }

                        // Response 전송
                        EmoServiceStopRP_m res = emoServiceStopRpBuilder
                                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStopRP))
                                .setMsgTime(System.currentTimeMillis())
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
                }, 5000, engineDeleteMsgs.size());
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        EmoServiceStopRP_m res = EmoServiceStopRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStopRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }

    private void deleteQueue(String queueName) {
        try {
            if (connectionInfo.getDeclaredQueues().remove(queueName)) {
                rmqModule.getChannel().queueDelete(queueName);
                log.info("Queue Deleted [{}]", queueName);
            } else {
                log.warn("Queue Not Declared [{}]", queueName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
