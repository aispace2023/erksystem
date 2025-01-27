package com.aispace.erksystem.connection;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.handler.base.RmqOutgoingHandler;
import com.aispace.erksystem.rmq.module.ErkEngineUtil.EngineMsgInfo;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.ErkEngineInfo_s;
import com.erksystem.protobuf.api.ServiceType_e;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static com.aispace.erksystem.common.SafeExecutor.tryRunSilent;
import static com.aispace.erksystem.connection.ConnectionInfo.State.IDLE;
import static com.aispace.erksystem.connection.ConnectionInfo.State.WAITING_ENGINE_RESPONSE;
import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineCreateMsgs;
import static com.aispace.erksystem.rmq.module.ErkEngineUtil.getEngineDeleteMsgs;

/**
 * Created by Ai_Space
 */
@Getter
@Setter
@Slf4j
public class ConnectionInfo {
    private static final UserConfig userConfig = AppInstance.getInstance().getUserConfig();
    private static final RmqManager rmqManager = RmqManager.getInstance();

    final int orgId;
    final int userId;
    final String key;

    long createTime = System.currentTimeMillis();
    long lastAccessTime = System.currentTimeMillis();

    AtomicReference<State> state = new AtomicReference<>(IDLE);
    AtomicReference<ServiceType_e> serviceType = new AtomicReference<>(ServiceType_e.ServiceType_unknown);
    CompletableFuture<Set<ErkEngineInfo_s>> cf = new CompletableFuture<>();
    Set<String> leftEngineList = new HashSet<>();
    Set<ErkEngineInfo_s> engineInfos = new HashSet<>();

    Set<String> declaredQueues = new HashSet<>();

    public ConnectionInfo(int orgId, int userId) {
        this.orgId = orgId;
        this.userId = userId;
        this.key = orgId + ":" + userId;
    }

    public void dealloc() {
        tryRunSilent(this::deleteDeclaredQueues);
    }

    public CompletableFuture<Set<ErkEngineInfo_s>> procEmoStart(ServiceType_e serviceType) {
        if (!state.compareAndSet(IDLE, WAITING_ENGINE_RESPONSE)) {
            throw new IllegalStateException("Illegal State");
        }

        leftEngineList.clear();
        engineInfos.clear();

        try {
            Set<EngineMsgInfo> engineCreateMsgs = getEngineCreateMsgs(serviceType, orgId, userId);

            for (EngineMsgInfo engineCreateMsg : engineCreateMsgs) {
                declareQueue(engineCreateMsg.getRecvQueue(), engineCreateMsg.getSendQueue());
                leftEngineList.add(engineCreateMsg.getTransactionId());
            }

            cf = new CompletableFuture<>();

            for (EngineMsgInfo engineCreateMsg : engineCreateMsgs) {
                RmqOutgoingHandler.send(engineCreateMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineCreateMsg.getEngineType()));
            }

            return cf;
        } catch (Exception e) {
            deleteDeclaredQueues();
            state.set(IDLE);
            throw e;
        }
    }

    public CompletableFuture<Set<ErkEngineInfo_s>> procEmoStop(ServiceType_e serviceType) {
        if (!state.compareAndSet(IDLE, WAITING_ENGINE_RESPONSE)) {
            throw new IllegalStateException("Illegal State");
        }

        leftEngineList.clear();
        engineInfos.clear();

        try {
            Set<EngineMsgInfo> engineDeleteMsgs = getEngineDeleteMsgs(serviceType, orgId, userId);

            for (EngineMsgInfo engineDeleteMsg : engineDeleteMsgs) {
                deleteQueue(engineDeleteMsg.getRecvQueue(), engineDeleteMsg.getSendQueue());
                leftEngineList.add(engineDeleteMsg.getTransactionId());
            }

            cf = new CompletableFuture<>();

            for (EngineMsgInfo engineDeleteMsg : engineDeleteMsgs) {
                RmqOutgoingHandler.send(engineDeleteMsg.getErkApiMsg(), userConfig.getEngineQueueMap().get(engineDeleteMsg.getEngineType()));
            }

            return cf;
        } catch (Exception e) {
            state.set(IDLE);
            throw e;
        }
    }

    @Synchronized
    public void onEngineResponse(String transactionId, ErkEngineInfo_s erkEngineInfo) {
        if (state.get() != WAITING_ENGINE_RESPONSE) {
            throw new IllegalStateException("Illegal State");
        }

        if (!leftEngineList.remove(transactionId)) {
            throw new IllegalStateException("Unexpected Engine Response");
        }

        engineInfos.add(erkEngineInfo);

        if (leftEngineList.isEmpty()) {
            cf.complete(new HashSet<>(engineInfos));
            state.set(IDLE);
        }
    }

    public void updateAccessTime() {
        lastAccessTime = System.currentTimeMillis();
    }

    public String getLogPrefix() {
        return "(" + this.key + ") ";
    }

    public void declareQueue(String... queueNames) {
        for (String queueName : queueNames) {
            try {
                rmqManager.getRmqModule().getChannel().queueDeclare(queueName, userConfig.isAgentOptionDurable(), userConfig.isAgentOptionExclusive(), userConfig.isAgentOptionAutoDelete(), Map.of("x-queue-type", "stream"));
                declaredQueues.add(queueName);
                log.info("Queue Declared [{}]", queueName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteQueue(String... queueNames) {
        for (String queueName : queueNames) {
            try {
                if (declaredQueues.remove(queueName)) {
                    rmqManager.getRmqModule().getChannel().queueDelete(queueName);
                    log.info("Queue Deleted [{}]", queueName);
                } else {
                    log.warn("Queue Not Declared [{}]", queueName);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteDeclaredQueues() {
        Set<String> queueNames = new HashSet<>(declaredQueues);
        for (String queueName : queueNames) {
            tryRunSilent(() -> deleteQueue(queueName));
        }
    }

    enum State {
        IDLE,
        WAITING_ENGINE_RESPONSE,
    }
}
