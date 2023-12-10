package com.aispace.erksystem.service.scheduler.handler;

import com.aispace.erksystem.service.scheduler.handler.base.IntervalTaskUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class StateMonitor extends IntervalTaskUnit {

    public StateMonitor() {
        super(1000);
    }

    @Override
    public void run() {
        log.debug("Connection count={}", connectionManager.getCloneConnectionInfos().size());
    }
}