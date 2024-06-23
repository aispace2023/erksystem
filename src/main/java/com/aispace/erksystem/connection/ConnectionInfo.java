package com.aispace.erksystem.connection;

import com.aispace.erksystem.common.utils.PromiseInfo;
import com.aispace.erksystem.common.utils.PromiseManager;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.module.RmqModule;
import com.erksystem.protobuf.api.EngineType_e;
import com.erksystem.protobuf.api.ErkEngineInfo_s;
import com.erksystem.protobuf.api.ServiceType_e;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ai_Space
 */
@Getter
@Setter
public class ConnectionInfo {
    final int orgId;
    final int userId;
    final String key;

    long createTime = System.currentTimeMillis();
    long lastAccessTime = System.currentTimeMillis();

    ServiceType_e serviceType;

    Map<EngineType_e, ErkEngineInfo_s> engineInfoMap = new HashMap<>();

    Set<String> declaredQueues = new HashSet<>();
    AtomicInteger promiseCountdown = new AtomicInteger();
    PromiseInfo promiseInfo = null;

    public ConnectionInfo(int orgId, int userId) {
        this.orgId = orgId;
        this.userId = userId;
        this.key = orgId + ":" + userId;
    }

    @Synchronized
    public ConnectionInfo setServiceType(ServiceType_e serviceType) {
        if (serviceType != null) {
            throw new IllegalStateException("ServiceType is already set");
        }
        this.serviceType = serviceType;
        return this;
    }

    public void dealloc() {
        RmqModule rmqModule = RmqManager.getInstance().getRmqModule();

        for (String createdQueue : declaredQueues) {
            try {
                rmqModule.getChannel().queueDelete(createdQueue);
            } catch (IOException e) {
                // Do Nothing
            }
        }
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
}
