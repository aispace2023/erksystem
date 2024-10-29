package com.aispace.erksystem.common;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class SafeExecutor {
    private SafeExecutor() {
    }

    public static void tryRun(ThrowableRunnable r) {
        try {
            r.run();
        } catch (Exception e) {
            log.warn("Err Occurs", e);
        }
    }

    public static void tryRunSilent(ThrowableRunnable r) {
        try {
            r.run();
        } catch (Exception e) {
            // Ignore
        }
    }

    @FunctionalInterface
    public interface ThrowableRunnable {
        void run() throws Exception;
    }

}
