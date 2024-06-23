package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.aispace.erksystem.service.AppInstance.RECV_QUEUE_NAME_PATTERN;
import static com.aispace.erksystem.service.AppInstance.SEND_QUEUE_NAME_PATTERN;

/**
 * @author kangmoo Heo
 */
public class ErkEngineUtil {
    private static final UserConfig userConfig = AppInstance.getInstance().getUserConfig();

    @Data
    @AllArgsConstructor
    public static class EngineMsgInfo {
        ErkApiMsg erkApiMsg;
        EngineType_e engineType;
        String sendQueue;
        String recvQueue;
    }

    public static Set<EngineMsgInfo> getEngineCreateMsgs(ServiceType_e serviceType, int orgId, int userId) {
        Set<EngineMsgInfo> res = new HashSet<>();

        List<EngineType_e> engineTypeEs = userConfig.getServiceTypeMap().get(serviceType);
        if (engineTypeEs == null)
            return res;

        for (EngineType_e engineType : engineTypeEs) {
            ErkEngineCreateRQ_m.Builder erkEngineCreateRqBuilder = ErkEngineCreateRQ_m.newBuilder();

            String sendQueue = SEND_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
            String recvQueue = RECV_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);

            switch (engineType) {
                case EngineType_physiology -> {
                    erkEngineCreateRqBuilder.setPhysioEngineSendQueueName(sendQueue);
                    erkEngineCreateRqBuilder.setPhysioEngineReceiveQueueName(recvQueue);
                }
                case EngineType_speech -> {
                    erkEngineCreateRqBuilder.setSpeechEngineSendQueueName(sendQueue);
                    erkEngineCreateRqBuilder.setSpeechEngineReceiveQueueName(recvQueue);
                }
                case EngineType_face -> {
                    erkEngineCreateRqBuilder.setFaceEngineSendQueueName(sendQueue);
                    erkEngineCreateRqBuilder.setFaceEngineReceiveQueueName(recvQueue);
                }
                case EngineType_knowledge -> {
                    erkEngineCreateRqBuilder.setKnowledgeEngineSendQueueName(sendQueue);
                    erkEngineCreateRqBuilder.setKnowledgeEngineReceiveQueueName(recvQueue);
                }
            }

            ErkApiMsg msg = ErkMsgWrapper.wrap2ErkApiMsg(erkEngineCreateRqBuilder
                    .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                            .setMsgType(ErkInterMsgType_e.ErkEngineCreateRQ)
                            .setOrgId(orgId)
                            .setUserId(userId))
                    .setMsgTime(System.currentTimeMillis())
                    .setServiceType(serviceType).build());

            res.add(new EngineMsgInfo(msg, engineType, sendQueue, recvQueue));
        }
        return res;
    }

    public static Set<EngineMsgInfo> getEngineDeleteMsgs(ServiceType_e serviceType, int orgId, int userId) {
        Set<EngineMsgInfo> res = new HashSet<>();

        List<EngineType_e> engineTypeEs = userConfig.getServiceTypeMap().get(serviceType);
        if (engineTypeEs == null)
            return res;

        for (EngineType_e engineType : engineTypeEs) {
            ErkEngineDeleteRQ_m.Builder erkEngineDeleteRqBuilder = ErkEngineDeleteRQ_m.newBuilder();

            String sendQueue = SEND_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
            String recvQueue = RECV_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);

            switch (engineType) {
                case EngineType_physiology -> {
                    erkEngineDeleteRqBuilder.setPhysioEngineSendQueueName(sendQueue);
                    erkEngineDeleteRqBuilder.setPhysioEngineReceiveQueueName(recvQueue);
                }
                case EngineType_speech -> {
                    erkEngineDeleteRqBuilder.setSpeechEngineSendQueueName(sendQueue);
                    erkEngineDeleteRqBuilder.setSpeechEngineReceiveQueueName(recvQueue);
                }
                case EngineType_face -> {
                    erkEngineDeleteRqBuilder.setFaceEngineSendQueueName(sendQueue);
                    erkEngineDeleteRqBuilder.setFaceEngineReceiveQueueName(recvQueue);
                }
                case EngineType_knowledge -> {
                    erkEngineDeleteRqBuilder.setKnowledgeEngineSendQueueName(sendQueue);
                    erkEngineDeleteRqBuilder.setKnowledgeEngineReceiveQueueName(recvQueue);
                }
            }

            ErkApiMsg msg = ErkMsgWrapper.wrap2ErkApiMsg(erkEngineDeleteRqBuilder
                    .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                            .setMsgType(ErkInterMsgType_e.ErkEngineDeleteRQ)
                            .setOrgId(orgId)
                            .setUserId(userId))
                    .setMsgTime(System.currentTimeMillis())
                    .setServiceType(serviceType).build());

            res.add(new EngineMsgInfo(msg, engineType, sendQueue, recvQueue));
        }
        return res;
    }
}
