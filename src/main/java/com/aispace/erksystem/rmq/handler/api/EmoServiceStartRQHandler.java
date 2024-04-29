package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.*;

import static com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper.convertToErkApiMsg;
import static com.erksystem.protobuf.api.ServiceType_e.*;
import static com.erksystem.protobuf.api.EngineType_e.*;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
    private static final String SEND_QUEUE_NAME_PATTERN = "SEND_%02d_%03d_%03d"; // SEND_{2자리 : enum EngineType_e }_{3자리 : OrgId}_{3자리 : UserId}
    private static final String RECV_QUEUE_NAME_PATTERN = "RECV_%02d_%03d_%03d"; // RECV_{2자리 : enum EngineType_e }_{3자리 : OrgId}_{3자리 : UserId}
    private static final List<Method> emoServiceStartRpMethods = Arrays.stream(EmoServiceStartRP_m.Builder.class.getDeclaredMethods()).toList();
    private static final List<Method> erkEngineCreateRqMethods = Arrays.stream(ErkEngineCreateRQ_m.Builder.class.getDeclaredMethods()).toList();

    private static final Map<ServiceType_e, List<EngineType_e>> TYPE_MAP = new HashMap<>() {
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

    EmoServiceStartRP_m.Builder emoServiceStartRpBuilder = EmoServiceStartRP_m.newBuilder();
    ErkEngineCreateRQ_m.Builder erkEngineCreateRqBuilder = ErkEngineCreateRQ_m.newBuilder();

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

        RmqModule rmqModule = rmqManager.getRmqModule(userConfig.getRmqIncomingQueueSubsystem()).orElseThrow();

        for (EngineType_e engineType : TYPE_MAP.get(msg.getServiceType())) {
            try {
                String sendQueue = SEND_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                String recvQueue = RECV_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                log.info("Queue Declared [{}]", recvQueue);
                rmqModule.getChannel().queueDeclare(recvQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                log.info("Queue Declared [{}]", sendQueue);
                rmqModule.getChannel().queueDeclare(sendQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));

                ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder().setEngineType(engineType)
                        //.setEngineCondition() // TODO : 엔진 상태 확인 및 설정
                        //.setIpAddr() // TODO : IP 주소 설정
                        .setSendQueueName(sendQueue)
                        .setReceiveQueueName(recvQueue)
                        .build();

/*                String methodPrefix = switch (engineType) {
                    case EngineType_physiology -> "setPhysioEngine";
                    case EngineType_speech -> "setSpeechEngine";
                    case EngineType_face -> "setFaceEngine";
                    case EngineType_knowledge -> "setKnowledgeEngine";
                    default -> throw new RmqHandleException(0, "Invalid EngineType [" + engineType + "]");
                };

                erkEngineCreateRqMethods.stream().filter(o -> o.getName().startsWith(methodPrefix))
                        .filter(o -> o.getName().endsWith("SendQueueName"))
                        .findAny().orElseThrow().invoke(erkEngineCreateRqBuilder, sendQueue);
                erkEngineCreateRqMethods.stream().filter(o -> o.getName().startsWith(methodPrefix))
                        .filter(o -> o.getName().endsWith("ReceiveQueueName"))
                        .findAny().orElseThrow().invoke(erkEngineCreateRqBuilder, recvQueue);
                emoServiceStartRpMethods.stream().filter(o -> o.getName().equals(methodPrefix + "Info"))
                        .findAny().orElseThrow().invoke(emoServiceStartRpBuilder, engineInfo);*/


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

                        // TODO : 테스트용 코드. 추후 ErkEngineCreateRQ_m 메시지 송신 로직 수정
                        ErkEngineInfo_s.Builder builder = engineInfo.toBuilder();
                        if(userConfig.getTestSendQueue() != null && !userConfig.getTestSendQueue().isEmpty()) {
                            builder.setSendQueueName(userConfig.getTestSendQueue());
                        }
                        if(userConfig.getTestRecvQueue() != null && !userConfig.getTestRecvQueue().isEmpty()) {
                            builder.setReceiveQueueName(userConfig.getTestRecvQueue());
                        }
                        emoServiceStartRpBuilder.setSpeechEngineInfo(builder.build());
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

        // TODO : 테스트용. 추후 삭제
        try {

            String testRecvQueue = userConfig.getTestRecvQueue();
            if (testRecvQueue != null && !testRecvQueue.isEmpty()) {
                rmqModule.getChannel().queueDeclare(testRecvQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                log.info("Queue Declared [{}]", testRecvQueue);
            }

            String testSendQueue = userConfig.getTestSendQueue();
            if (testSendQueue != null && !testSendQueue.isEmpty()) {
                rmqModule.getChannel().queueDeclare(testSendQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                log.info("Queue Declared [{}]", testSendQueue);
            }
        } catch (Exception e) {
            // Do Nothing
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

        // TODO : 테스트용 코드. 추후 ErkEngineCreateRQ_m 메시지 송신 로직 수정
        Optional.ofNullable(userConfig.getTestSendQueue()).ifPresent(o -> erkEngineCreateRqBuilder.setSpeechEngineSendQueueName(o));
        Optional.ofNullable(userConfig.getTestRecvQueue()).ifPresent(o -> erkEngineCreateRqBuilder.setSpeechEngineReceiveQueueName(o));

        ErkApiMsg msg = convertToErkApiMsg(erkEngineCreateRqBuilder
                .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                        .setMsgType(ErkInterMsgType_e.ErkEngineCreateRQ)
                        .setOrgId(orgId)
                        .setUserId(userId))
                .setMsgTime(System.currentTimeMillis())
                .setServiceType(this.msg.getServiceType()).build());
        RmqOutgoingHandler.send(msg, userConfig.getRmqIncomingQueueSubsystem(), userConfig.getRmqIncomingQueueSubsystem());
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }
}
