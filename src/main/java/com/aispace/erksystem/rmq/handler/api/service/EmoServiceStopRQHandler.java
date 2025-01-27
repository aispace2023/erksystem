package com.aispace.erksystem.rmq.handler.api.service;

import com.aispace.erksystem.connection.ConnectionInfo;
import com.aispace.erksystem.rmq.handler.base.RmqIncomingHandler;
import com.erksystem.protobuf.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_ok;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;

/**
 * Created by Ai_Space
 */
@Slf4j
public class EmoServiceStopRQHandler extends RmqIncomingHandler<EmoServiceStopRQ_m> {
    private ConnectionInfo connectionInfo;

    @Override
    protected void handle() {
        ErkMsgHead_s erkMsgHead = msg.getErkMsgHead();
        int orgId = erkMsgHead.getOrgId();
        int userId = erkMsgHead.getUserId();
        ServiceType_e serviceType = msg.getServiceType();

        connectionInfo = connectionManager.findConnectionInfo(orgId, userId).orElseThrow();
        ServiceType_e currentServiceType = connectionInfo.getServiceType().get();
        if (currentServiceType != serviceType) {
            throw new IllegalArgumentException("ServiceType not matched. Expected [" + currentServiceType + "] but got [" + serviceType + "]");
        }

        CompletableFuture<Set<ErkEngineInfo_s>> completableFuture = connectionInfo.procEmoStop(serviceType);

        completableFuture
                .orTimeout(5000, TimeUnit.MILLISECONDS)
                .thenAccept(this::onSuccess)
                .exceptionally(e -> {
                    log.warn("Fail to Stop EmoService", e);
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
        EmoServiceStopRP_m res = EmoServiceStopRP_m.newBuilder()
                .setErkMsgHead(ErkMsgHead_s.newBuilder(msg.getErkMsgHead()).setMsgType(ErkMsgType_e.EmoServiceStopRP))
                .setMsgTime(System.currentTimeMillis())
                .setReturnCodeValue(reasonCode)
                .build();

        reply(res);
    }

    private void onSuccess(Set<ErkEngineInfo_s> engineInfos) {
        try {
            EmoServiceStopRP_m.Builder emoServiceStopRpBuilder = EmoServiceStopRP_m.newBuilder();

            for (ErkEngineInfo_s engineInfo : engineInfos) {
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
                    .setReturnCode(ReturnCode_ok)
                    .build();
            reply(res);
        } catch (Exception e) {
            log.warn("Unexpected Exception Occurs", e);
            onFail(ReturnCode_unknown.getNumber(), "Unexpected Exception");
        }
    }
}
