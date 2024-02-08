package com.aispace.erksystem.rmq.handler.api;

import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStopRQHandler extends RmqIncomingHandler<EmoServiceStopRQ_m> {
    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        /* 추후 개발/개선
        ServiceUser user = ServiceUserDAO.read(erkMsgHead.getUserId(), erkMsgHead.getOrgId());
        if (user == null) {
            throw new RmqHandleException(0, "User is not exists");
        }
        */

        String recvQueue ="RECV_%02d_%03d_%03d".formatted(msg.getServiceType().getNumber(), erkMsgHead.getOrgId(), erkMsgHead.getUserId());
        String sendQueue ="SEND_%02d_%03d_%03d".formatted(msg.getServiceType().getNumber(), erkMsgHead.getOrgId(), erkMsgHead.getUserId());

        // RMQ 큐 삭제
        RmqModule rmqModule = rmqManager.getRmqModule(userConfig.getRmqIncomingQueueSubsystem()).orElseThrow();
        try {
            rmqModule.queueDelete(recvQueue);
            rmqModule.queueDelete(sendQueue);
        } catch (Exception e){
            throw new RmqHandleException(0, "Fail to delete queue", e);
        }

        // 추후 메시지에 TransactionId 필드가 추가되면 그때 key 수정
        String key = erkMsgHead.getMsgType().toString() + erkMsgHead.getUserId() + erkMsgHead.getOrgId();
        promiseManager.createPromiseInfo(key,
                () -> {
                    try {
                        // Response 전송
                        EmoServiceStopRP_m res = EmoServiceStopRP_m.newBuilder()
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
        // TODO ErkEngineDeleteRQ_m 송신
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
