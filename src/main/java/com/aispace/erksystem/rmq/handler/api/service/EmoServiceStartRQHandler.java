package com.aispace.erksystem.rmq.handler.api.service;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.erksystem.protobuf.api.ReturnCode_e.*;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStartRQHandler extends RmqIncomingHandler<EmoServiceStartRQ_m> {
    private ConnectionInfo connectionInfo;

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();
        ServiceType_e serviceType = msg.getServiceType();

        connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();

        CompletableFuture<Set<ErkEngineInfo_s>> completableFuture = connectionInfo.procEmoStart(serviceType);

        completableFuture
                .orTimeout(5000, TimeUnit.MILLISECONDS)
                .thenAccept(this::onSuccess)
                .exceptionally(e -> {
                    log.warn("Fail to Start EmoService", e);
                    if (e instanceof TimeoutException) {
                        onFail(ReturnCode_unknown.getNumber(), "Engine not responding");
                    } else {
                        onFail(ReturnCode_unknown.getNumber(), "Fail to Start EmoService");
                    }
                    return null;
                });
    }

    @Override
    protected void onFail(int reasonCode, String reason) {
        // 실패 시 생성한 큐 삭제
        if (connectionInfo != null) {
            connectionInfo.deleteDeclaredQueues();
        }

        EmoServiceStartRP_m res = EmoServiceStartRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStartRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }

    private void onSuccess(Set<ErkEngineInfo_s> engineInfos) {
        try {
            EmoServiceStartRP_m.Builder emoServiceStartRpBuilder = EmoServiceStartRP_m.newBuilder();

            for (ErkEngineInfo_s engineInfo : engineInfos) {
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
    }
}
