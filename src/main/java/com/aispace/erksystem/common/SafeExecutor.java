package com.aispace.erksystem.common;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ai_Space
 */
@Slf4j
public class SafeExecutor {
    private SafeExecutor() {
    }

    public static void tryRun(Runnable r) {
        try {
            r.run();
        } catch (Exception e) {
            log.warn("Err Occurs", e);
        }
    }

    public static Runnable safeRunnable(Runnable r) {
        return () -> tryRun(r);
    }

    public static void tryRunWithRetries(Runnable r, int retries) {
        for (int i = 0; i < retries; i++) {
            try {
                r.run();
                return;
            } catch (Exception e) {
                log.warn("Error occurs on try " + (i + 1), e);
            }
        }
    }

}
