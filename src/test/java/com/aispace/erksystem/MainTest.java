package com.aispace.erksystem;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Created by Ai_Space
 */
public class MainTest {
    @Test
    void mainRun() {
        new Thread(() -> {
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.exit(0);
            }
        }).start();


        ErkSystemMain.main(new String[]{getClass().getClassLoader().getResource("config").getPath()});
    }
}
