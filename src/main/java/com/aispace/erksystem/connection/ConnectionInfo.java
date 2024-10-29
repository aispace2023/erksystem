package com.aispace.erksystem.connection;

import com.aispace.erksystem.common.utils.PromiseInfo;
import com.aispace.erksystem.common.utils.PromiseManager;
import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.EngineType_e;
import com.erksystem.protobuf.api.ErkEngineInfo_s;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static com.aispace.erksystem.common.SafeExecutor.tryRunSilent;

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

    Map<EngineType_e, ErkEngineInfo_s> engineInfoMap = new HashMap<>();

    Set<String> declaredQueues = new HashSet<>();
    AtomicInteger promiseCountdown = new AtomicInteger();
    PromiseInfo promiseInfo = null;

    public ConnectionInfo(int orgId, int userId) {
        this.orgId = orgId;
        this.userId = userId;
        this.key = orgId + ":" + userId;
    }

    public void dealloc() {
        tryRunSilent(this::deleteDeclaredQueues);
    }

    public void addPromise(Runnable onSuccess, Runnable onFail, Runnable onTimeout, long timeoutMs, int count) {
        if (!promiseCountdown.compareAndSet(0, count)) {
            throw new IllegalStateException("Current promiseCountdown is not 0");
        }
        promiseInfo = PromiseManager.getInstance().createPromiseInfo(this.key, onSuccess, onFail, onTimeout, timeoutMs);
    }

    public void countDownPromise() {
        if (promiseCountdown.decrementAndGet() == 0) {
            promiseInfo.procSuccess();
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
        for (String declaredQueue : declaredQueues) {
            tryRunSilent(() -> deleteQueue(declaredQueue));
        }
    }
}
