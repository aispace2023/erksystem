package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

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
        String sendQueue = "RECV_%02d_%03d_%03d".formatted(msg.getServiceType().getNumber(), orgId, userId);

        RmqModule rmqModule = rmqManager.getRmqModule(userConfig.getRmqIncomingQueueSubsystem()).orElseThrow();

        // RMQ 큐 생성
        try {
            rmqModule.getChannel().queueDeclare(recvQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
            rmqModule.getChannel().queueDeclare(sendQueue, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
        } catch (IOException e) {
            throw new RmqHandleException(0, "Fail to create queue", e);
        }
        // 추후 메시지에 TransactionId 필드가 추가되면 그때 key 수정
        String key = erkMsgHead.getMsgType().toString() + userId + orgId;
        promiseManager.createPromiseInfo(key,
                () -> {
                    try {
                        // Response 전송
                        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                                .setMsgTime(System.currentTimeMillis())
                                .build();
                        RmqOutgoingHandler.sendErkApiMsg2API(res);
                    } catch (Exception e) {
                        log.warn("Unexpected Exception Occurs", e);
                        onFail(0, "Unexpected Exception");
                    }
                },
                () -> onFail(0, "EmoServiceStart Fail"),
                () -> onFail(0, "EmoServiceStart Timeout"), 5000);
        // TODO ErkEngineCreateRQ_m 송신
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        RmqOutgoingHandler.sendErkApiMsg2API(res);
    }
}
