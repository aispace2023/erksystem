package com.aispace.erksystem.service;

import com.aispace.erksystem.common.SystemLock;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.service.database.DBManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ServiceManager {
    private static final ServiceManager INSTANCE = new ServiceManager();
    private static final String CONFIG_FILE_NAME = "user_config.yaml";
    private static final String PROCESS_NAME = "erk_service";
    private static final AppInstance appInstance = AppInstance.getInstance();

    @Getter
    @Setter
    private static boolean isQuit = true;

    private ServiceManager() {
    }

    public static void startService(String configPath) throws IOException, NoSuchFieldException, InterruptedException, TimeoutException {
        // 프로세스 중복 실행 방지
        SystemLock.lock(PROCESS_NAME + ".lock");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("Process is about to quit");
            stopService();
        }));

        // Config 초기화
        appInstance.getConfig().init(Path.of(configPath).resolve(CONFIG_FILE_NAME).toString());

        // RMQ 서버 연결 및 RMQ Consumer 등록
        RmqManager.start();
        // DATABASE 연결
        //DBManager.getInstance().start();

        isQuit = false;
        log.info("Process startup succeeded");

        // 서비스가 중단될 때까지 Blocking
        while (!isQuit) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void stopService() {
        //DBManager.getInstance().stop();
        RmqManager.stop();
        isQuit = true;
    }
}
