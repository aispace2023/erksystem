package com.aispace.erksystem.session;

import com.aispace.erksystem.rmq.handler.base.exception.ValidationHandleException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    private static final Map<String, SessionInfo> sessionInfos = new ConcurrentHashMap<>();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public SessionInfo createSession(String sessionId, int count) {
        SessionInfo sessionInfo = new SessionInfo(sessionId, count);
        if (sessionInfos.putIfAbsent(sessionId, sessionInfo) != null) {
            throw new ValidationHandleException(-1, "Session Info Already Exist"); //  TODO : Define Reason Code
        }
        log.info("Session Created [{}]", sessionId);
        return sessionInfo;
    }

    public Optional<SessionInfo> getSessionInfo(String sessionId) {
        return Optional.ofNullable(sessionInfos.get(sessionId));
    }

    public void deleteSession(String sessionId) {
        if(sessionInfos.remove(sessionId) == null){
            log.info("Session Already deleted. Remaining count [{}]", sessionInfos.size());
        }
    }

    public Map<String, SessionInfo> getCloneSessionInfos() {
        return new HashMap<>(sessionInfos);
    }
}
