package com.aispace.erksystem.service.scheduler.handler;

import com.aispace.erksystem.service.scheduler.handler.base.IntervalTaskUnit;
import com.aispace.erksystem.session.SessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class SessionChecker extends IntervalTaskUnit {
    SessionManager sessionManager = SessionManager.getInstance();

    public SessionChecker() {
        super(1000);
    }

    @Override
    public void run() {
        sessionManager.getCloneSessionInfos().values().stream()
                .filter(sessionInfo -> System.currentTimeMillis() - sessionInfo.getCreateTime() > 30_000)
                .forEach(o -> {
                    log.warn("Session Deleted by Timeout [{}]", o.getSessionId());
                    sessionManager.deleteSession(o.getSessionId());
                });
    }
}