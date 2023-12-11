package com.aispace.erksystem.rmq.module.handler.api;

import com.aispace.erksystem.rmq.module.handler.base.RmqIncomingHandler;
import com.aispace.erksystem.rmq.module.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.module.handler.base.RmqOutgoingHandler.sendErkApiMsg2API;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStopRQHandler extends RmqIncomingHandler<EmoServiceStopRQ_m> {
    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        ServiceUser user = ServiceUserDAO.read(erkMsgHead.getUserId(), erkMsgHead.getOrgId());
        if (user == null) {
            throw new RmqHandleException(0, "User is not exists");
        }

        // TODO TransactionId 없이 이러한 절차를 진행하는 것이 적절한지에 대해 확인 필요
        String key = erkMsgHead.getMsgType().toString() + erkMsgHead.getUserId() + erkMsgHead.getOrgId();
        promiseManager.createPromiseInfo(key,
                () -> {
                    try {
                        // RMQ 큐 삭제
                        rmqManager.getRmqModule(userConfig.getRmqIncomingQueueSubsystem()).orElseThrow()
                                .queueDelete(key); // TODO Queue Name 구조는 팀장님이 정의해주실 예정. 추후 수정

                        // Response 전송
                        EmoServiceStopRP_m res = EmoServiceStopRP_m.newBuilder()
                                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStopRP))
                                .setMsgTime(System.currentTimeMillis())
                                .build();
                        sendErkApiMsg2API(res);
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

        sendErkApiMsg2API(res);
    }
}
