package com.aispace.erksystem.service.scheduler;

import com.aispace.erksystem.service.scheduler.handler.ConnectionChecker;
import com.aispace.erksystem.service.scheduler.handler.StateMonitor;
import com.aispace.erksystem.service.scheduler.handler.base.IntervalTaskUnit;

import java.util.List;

/**
 * Session Manager
 * 주기적으로 세션을 필터링하고 처리
 *
 * Created by Ai_Space
 */
public class IntervalTaskManager {
    private static final List<IntervalTaskUnit> itu = List.of(
            new ConnectionChecker(),
            new StateMonitor()
    );

    private IntervalTaskManager() {
    }

    public static void startAll() {
        itu.forEach(IntervalTaskUnit::start);
    }

    public static void stopAll() {
        itu.forEach(IntervalTaskUnit::stop);
    }
}