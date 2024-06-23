package com.aispace.erksystem.connection;

import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ConnectionManager {
    private static final ConnectionManager INSTANCE = new ConnectionManager();
    private static final Map<String, ConnectionInfo> connectionInfos = new ConcurrentHashMap<>();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    public ConnectionInfo createConnection(int orgId, int userId) {
        ConnectionInfo connectionInfo = new ConnectionInfo(orgId, userId);
        if (connectionInfos.putIfAbsent(getKey(orgId, userId), connectionInfo) != null) {
            log.warn("[{}] connection is already exist", userId);
            throw new RmqHandleException(0, "Connection Already Exist");
        }
        log.info("{} Connection Created", connectionInfo.getLogPrefix());
        return connectionInfo;
    }

    public Optional<ConnectionInfo> findConnectionInfo(int orgId, int userId) {
        return Optional.ofNullable(connectionInfos.get(getKey(orgId, userId)));
    }

    public Set<ConnectionInfo> findConnectionInfo(Predicate<ConnectionInfo> predicates) {
        return connectionInfos.values().stream().filter(predicates).collect(Collectors.toSet());
    }

    public void deleteConnection(int orgId, int userId) {
        deleteConnection(getKey(orgId, userId));
    }

    public void deleteConnection(String key) {
        ConnectionInfo removedConnectionInfo = connectionInfos.remove(key);
        if (removedConnectionInfo == null) {
            log.info("[{}] Connection Already deleted. Remaining count [{}]", key, connectionInfos.size());
            return;
        }
        removedConnectionInfo.dealloc();

    }

    public int getConnectionCount() {
        return connectionInfos.size();
    }

    public String getKey(int orgId, int userId) {
        return orgId + ":" + userId;
    }
}
