package com.aispace.erksystem;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Ai_Space
 */
public class MainTest {
    @Test
    void mainRun() throws InterruptedException, IOException {

        // Start Main Thread
        Thread mainThread = new Thread(() -> {
            ErkSystemMain.main(new String[]{getClass().getClassLoader().getResource("config").getPath()});
        });
        mainThread.start();

        Thread.sleep(3000);

        // RMQ Message 송신 (로그로 수신 확인)
        RmqManager.sendMessage("INCOMING_QUEUE_TEST", "Hello World!".getBytes());

        Thread.sleep(3000);
    }
}
