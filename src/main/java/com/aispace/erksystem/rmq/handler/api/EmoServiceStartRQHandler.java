package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

import static com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper.convertToErkApiMsg;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
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
        String recvQueue = "RECV_%02d_%03d_%03d".formatted(msg.getServiceType().getNumber(), orgId, userId);
        String sendQueue = "SEND_%02d_%03d_%03d".formatted(msg.getServiceType().getNumber(), orgId, userId);

        RmqModule rmqModule = rmqManager.getRmqModule(userConfig.getRmqIncomingQueueSubsystem()).orElseThrow();

        // RMQ 큐 생성
        try {
            rmqModule.getChannel().queueDeclare(recvQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
            log.info("Queue Declared [{}]", recvQueue);
            rmqModule.getChannel().queueDeclare(sendQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
            log.info("Queue Declared [{}]", sendQueue);
        } catch (IOException e) {
            throw new RmqHandleException(0, "Fail to create queue", e);
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
                        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
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
        ErkApiMsg msg = convertToErkApiMsg(ErkEngineCreateRQ_m.newBuilder()
                .setErkInterMsgHead(ErkInterMsgHead_s.newBuilder()
                        .setMsgType(ErkInterMsgType_e.ErkEngineCreateRQ)
                        .setOrgId(orgId)
                        .setUserId(userId))
                .setMsgTime(System.currentTimeMillis())
                .setServiceType(this.msg.getServiceType())
                .setSpeechEngineSendQueueName(userConfig.getTestSendQueue())
                .setSpeechEngineReceiveQueueName(userConfig.getTestRecvQueue()).build());
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
