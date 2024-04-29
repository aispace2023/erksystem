package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.*;

import static com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper.convertToErkApiMsg;
import static com.aispace.erksystem.service.AppInstance.RECV_QUEUE_NAME_PATTERN;
import static com.aispace.erksystem.service.AppInstance.SEND_QUEUE_NAME_PATTERN;
import static com.erksystem.protobuf.api.EngineCondition_e.*;
import static com.erksystem.protobuf.api.ServiceType_e.*;
import static com.erksystem.protobuf.api.EngineType_e.*;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
    public static final Map<ServiceType_e, List<EngineType_e>> TYPE_MAP = new HashMap<>() {
        {
            put(ServiceType_physiology, List.of(EngineType_physiology));
            put(ServiceType_speech, List.of(EngineType_speech));
            put(ServiceType_video, List.of(EngineType_face));
            put(ServiceType_physiology_speech, List.of(EngineType_physiology, EngineType_speech));
            put(ServiceType_physiology_video, List.of(EngineType_physiology, EngineType_face));
            put(ServiceType_speech_video, List.of(EngineType_speech, EngineType_face));
            put(ServiceType_physiology_speech_video, List.of(EngineType_physiology, EngineType_speech, EngineType_face));
            put(ServiceType_knowledge, List.of(EngineType_knowledge));
            put(ServiceType_service_all, List.of(EngineType_physiology, EngineType_speech, EngineType_face, EngineType_knowledge));
        }
    };

    private final EmoServiceStartRP_m.Builder emoServiceStartRpBuilder = EmoServiceStartRP_m.newBuilder();
    private final ErkEngineCreateRQ_m.Builder erkEngineCreateRqBuilder = ErkEngineCreateRQ_m.newBuilder();
    private final List<String> declaredQueues = new ArrayList<>();
    private final RmqModule rmqModule = rmqManager.getRmqModule();

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        /*추후 개발/개선
        ServiceUser user = ServiceUserDAO.read(erkMsgHead.getUserId(), erkMsgHead.getOrgId());
        if (user == null) {
            throw new RmqHandleException(0, "User is not exists");
        }
        */


        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();

        for (EngineType_e engineType : TYPE_MAP.get(msg.getServiceType())) {
            try {
                String sendQueue = SEND_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                String recvQueue = RECV_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                rmqModule.getChannel().queueDeclare(recvQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                log.info("Queue Declared [{}]", recvQueue);
                declaredQueues.add(recvQueue);
                rmqModule.getChannel().queueDeclare(sendQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                log.info("Queue Declared [{}]", sendQueue);
                declaredQueues.add(sendQueue);

                ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder().setEngineType(engineType)
                        .setEngineCondition(EngineCondition_available) // TODO : 엔진 상태 확인 및 설정
                        //.setIpAddr() // TODO : IP 주소 설정
                        .setSendQueueName(sendQueue)
                        .setReceiveQueueName(recvQueue)
                        .build();

                switch (engineType) {
                    case EngineType_physiology -> {
                        erkEngineCreateRqBuilder.setPhysioEngineSendQueueName(sendQueue);
                        erkEngineCreateRqBuilder.setPhysioEngineReceiveQueueName(recvQueue);
                        emoServiceStartRpBuilder.setPhysioEngineInfo(engineInfo);
                    }
                    case EngineType_speech -> {
                        erkEngineCreateRqBuilder.setSpeechEngineSendQueueName(sendQueue);
                        erkEngineCreateRqBuilder.setSpeechEngineReceiveQueueName(recvQueue);
                        emoServiceStartRpBuilder.setSpeechEngineInfo(engineInfo);
                    }
                    case EngineType_face -> {
                        erkEngineCreateRqBuilder.setFaceEngineSendQueueName(sendQueue);
                        erkEngineCreateRqBuilder.setFaceEngineReceiveQueueName(recvQueue);
                        emoServiceStartRpBuilder.setFaceEngineInfo(engineInfo);
                    }
                    case EngineType_knowledge -> {
                        erkEngineCreateRqBuilder.setKnowledgeEngineSendQueueName(sendQueue);
                        erkEngineCreateRqBuilder.setKnowledgeEngineReceiveQueueName(recvQueue);
                        emoServiceStartRpBuilder.setKnowledgeEngineInfo(engineInfo);
                    }
                }
            } catch (Exception e) {
                throw new RmqHandleException(0, "Fail to create queue", e);
            }
        }

        // 추후 메시지에 TransactionId 필드가 추가되면 그때 key 수정
        String key = userId + "_" + orgId;
        promiseManager.createPromiseInfo(key,
                () -> {
                    try {
                        // Response 전송
                        EmoServiceStartRP_m res = emoServiceStartRpBuilder
                                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                                .setMsgTime(System.currentTimeMillis())
                                .setReturnCode(ReturnCode_e.ReturnCode_ok)
                                .build();
                        reply(res);
                    } catch (Exception e) {
                        log.warn("Unexpected Exception Occurs", e);
                        onFail(0, "Unexpected Exception");
                    }
                },
                () -> onFail(0, "EmoServiceStart Fail"),
                () -> onFail(0, "EmoServiceStart Timeout"), 5000);
        ErkApiMsg replyMsg = convertToErkApiMsg(erkEngineCreateRqBuilder
                .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                        .setMsgType(ErkInterMsgType_e.ErkEngineCreateRQ)
                        .setOrgId(orgId)
                        .setUserId(userId))
                .setMsgTime(System.currentTimeMillis())
                .setServiceType(this.msg.getServiceType()).build());
        RmqOutgoingHandler.send(replyMsg, userConfig.getRmqIncomingQueueSubsystem());
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        for (String declaredQueue : declaredQueues) {
            try {
                rmqModule.getChannel().queueDelete(declaredQueue);
            } catch (Exception e){
                // Do Nothing
            }
        }
        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
