package com.aispace.erksystem.connection;

import com.aispace.erksystem.rmq.module.handler.base.exception.RmqHandleException;
import com.erksystem.protobuf.api.ServiceType_e;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ConnectionManager {
    private static final ConnectionManager INSTANCE = new ConnectionManager();
    private static final Map<Integer, ConnectionInfo> connectionInfos = new ConcurrentHashMap<>();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    public ConnectionInfo createConnection(Integer userId, Integer orgId) {
        ConnectionInfo connectionInfo = new ConnectionInfo(userId, orgId);
        if (connectionInfos.putIfAbsent(userId, connectionInfo) != null) {
            log.warn("[{}] connection is already exist", userId);
            throw new RmqHandleException(0, "Connection Already Exist");
        }
        log.info("{} Connection Created", connectionInfo.getLogPrefix());
        return connectionInfo;
    }

    public Optional<ConnectionInfo> findConnection(Integer userId) {
        return Optional.ofNullable(connectionInfos.get(userId));
    }

    public void deleteConnection(Integer userId) {
        ConnectionInfo removedConnectionInfo = connectionInfos.remove(userId);
        if (removedConnectionInfo == null) {
            log.info("[{}] Connection Already deleted. Remaining count [{}]", userId, connectionInfos.size());
            return;
        }
        removedConnectionInfo.dealloc();
    }

    public Map<Integer, ConnectionInfo> getCloneConnectionInfos() {
        return new HashMap<>(connectionInfos);
    }

    public String getLogPrefix(Integer userId) {
        return findConnection(userId).map(ConnectionInfo::getLogPrefix).orElseGet(() -> "(" + userId + ") () ");
    }
}
