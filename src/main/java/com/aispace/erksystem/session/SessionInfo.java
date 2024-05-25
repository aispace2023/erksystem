package com.aispace.erksystem.session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Slf4j
public class SessionInfo {
    String sessionId;
    AtomicInteger remainingCount;
    long createTime = System.currentTimeMillis();;

    public SessionInfo(String sessionId, int count) {
        this.sessionId = sessionId;
        this.remainingCount = new AtomicInteger(count);
    }
}
