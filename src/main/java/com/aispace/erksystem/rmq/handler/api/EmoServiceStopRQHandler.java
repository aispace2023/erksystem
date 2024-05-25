package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.aispace.erksystem.session.SessionManager;
import com.erksystem.protobuf.api.*;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.aispace.erksystem.rmq.handler.api.EmoServiceStartRQHandler.ENGINE_QUEUE_MAP;
import static com.aispace.erksystem.rmq.handler.api.EmoServiceStartRQHandler.TYPE_MAP;
import static com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper.convertToErkApiMsg;
import static com.aispace.erksystem.service.AppInstance.RECV_QUEUE_NAME_PATTERN;
import static com.aispace.erksystem.service.AppInstance.SEND_QUEUE_NAME_PATTERN;
import static com.erksystem.protobuf.api.EngineCondition_e.EngineCondition_available;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStopRQHandler extends RmqIncomingHandler<EmoServiceStopRQ_m> {
    private final EmoServiceStopRP_m.Builder emoServiceStopRpBuilder = EmoServiceStopRP_m.newBuilder();
    private final ErkEngineDeleteRQ_m.Builder erkEngineDeleteRqBuilder = ErkEngineDeleteRQ_m.newBuilder();

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        // TODO : User 정보 확인 로직

        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();

        // TODO : 추후 메시지에 TransactionId 필드가 추가되면 그때 key 수정
        String key = userId + "_" + orgId;
        List<EngineType_e> engineTypeEs = TYPE_MAP.get(msg.getServiceType());
        SessionManager.getInstance().createSession(userId + "_" + orgId, engineTypeEs.size());

        // RMQ 큐 삭제
        Channel rmqChannel = rmqManager.getRmqModule().getChannel();
        for (EngineType_e engineType : TYPE_MAP.get(msg.getServiceType())) {
            try {
                String sendQueue = SEND_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                rmqChannel.queueDelete(sendQueue);
                String recvQueue = RECV_QUEUE_NAME_PATTERN.formatted(engineType.getNumber(), orgId, userId);
                rmqChannel.queueDelete(recvQueue);

                ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder().setEngineType(engineType)
                        .setEngineCondition(EngineCondition_available) // TODO : 엔진 상태 확인 및 설정
                        //.setIpAddr() // TODO : IP 주소 설정
                        .setSendQueueName(sendQueue)
                        .setReceiveQueueName(recvQueue)
                        .build();

                ErkApiMsg engineDeleteMsg = convertToErkApiMsg(erkEngineDeleteRqBuilder
                        .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                                .setMsgType(ErkInterMsgType_e.ErkEngineDeleteRQ)
                                .setOrgId(orgId)
                                .setUserId(userId))
                        .setMsgTime(System.currentTimeMillis())
                        .setServiceType(this.msg.getServiceType()).build());

                switch (engineType) {
                    case EngineType_physiology -> {
                        erkEngineDeleteRqBuilder.setPhysioEngineSendQueueName(sendQueue);
                        erkEngineDeleteRqBuilder.setPhysioEngineReceiveQueueName(recvQueue);
                        emoServiceStopRpBuilder.setPhysioEngineInfo(engineInfo);
                        RmqOutgoingHandler.send(engineDeleteMsg, ENGINE_QUEUE_MAP.get(engineType));
                    }
                    case EngineType_speech -> {
                        erkEngineDeleteRqBuilder.setSpeechEngineSendQueueName(sendQueue);
                        erkEngineDeleteRqBuilder.setSpeechEngineReceiveQueueName(recvQueue);
                        emoServiceStopRpBuilder.setSpeechEngineInfo(engineInfo);
                        RmqOutgoingHandler.send(engineDeleteMsg, ENGINE_QUEUE_MAP.get(engineType));
                    }
                    case EngineType_face -> {
                        erkEngineDeleteRqBuilder.setFaceEngineSendQueueName(sendQueue);
                        erkEngineDeleteRqBuilder.setFaceEngineReceiveQueueName(recvQueue);
                        emoServiceStopRpBuilder.setFaceEngineInfo(engineInfo);
                        RmqOutgoingHandler.send(engineDeleteMsg, ENGINE_QUEUE_MAP.get(engineType));
                    }
                    case EngineType_knowledge -> {
                        erkEngineDeleteRqBuilder.setKnowledgeEngineSendQueueName(sendQueue);
                        erkEngineDeleteRqBuilder.setKnowledgeEngineReceiveQueueName(recvQueue);
                        emoServiceStopRpBuilder.setKnowledgeEngineInfo(engineInfo);
                        RmqOutgoingHandler.send(engineDeleteMsg, ENGINE_QUEUE_MAP.get(engineType));
                    }
                }

            } catch (Exception e) {
                throw new RmqHandleException(0, "Fail to delete queue", e);
            }
        }

        // 추후 메시지에 TransactionId 필드가 추가되면 그때 key 수정
        promiseManager.createPromiseInfo(key,
                () -> {
                    try {
                        // Response 전송
                        EmoServiceStopRP_m res = emoServiceStopRpBuilder
                                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStopRP))
                                .setMsgTime(System.currentTimeMillis())
                                .build();
                        reply(res);
                    } catch (Exception e) {
                        log.warn("Unexpected Exception Occurs", e);
                        onFail(0, "Unexpected Exception");
                    }
                },
                () -> onFail(0, "EmoServiceStart Fail"),
                () -> onFail(0, "EmoServiceStart Timeout"), 5000);
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
}
