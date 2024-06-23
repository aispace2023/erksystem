package com.aispace.erksystem.service.scheduler.handler;

import com.aispace.erksystem.service.scheduler.handler.base.IntervalTaskUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ConnectionChecker extends IntervalTaskUnit {
    public ConnectionChecker() {
        super(1000);
    }

    @Override
    public void run() {
        long connectionTimeout = userConfig.getConnectionTimeout() * 1000L;
        if (connectionTimeout <= 0) return;
        long now = System.currentTimeMillis();
        connectionManager.findConnectionInfo(connectionInfo -> connectionInfo.getLastAccessTime() + connectionTimeout < now)
                .forEach(connectionInfo -> {
                    log.warn("{} Disconnect Timeout conncetion", connectionInfo.getLogPrefix());
                    connectionManager.deleteConnection(connectionInfo.getKey());
                });
    }
}
