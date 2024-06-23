package com.aispace.erksystem.service.scheduler.handler;

import com.aispace.erksystem.service.scheduler.handler.base.IntervalTaskUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkServiceChecker extends IntervalTaskUnit {

    public ErkServiceChecker() {
        super(1000);
    }

    @Override
    public void run() {
        connectionManager.findConnectionInfo(connectionInfo -> System.currentTimeMillis() - connectionInfo.getLastAccessTime() > userConfig.getErkHbTimeout())
                .forEach(connectionInfo -> {
                    log.warn("{} ErkService Deleted by Timeout", connectionInfo.getLogPrefix());
                    connectionManager.deleteConnection(connectionInfo.getKey());
                });
    }
}