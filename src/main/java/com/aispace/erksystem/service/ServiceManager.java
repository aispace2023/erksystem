package com.aispace.erksystem.service;

import com.aispace.erksystem.common.SystemLock;
import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.module.ErkApiMsgRmqConsumer;
import com.aispace.erksystem.rmq.module.RmqStreamModule;
import com.aispace.erksystem.service.scheduler.IntervalTaskManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ServiceManager {
    private static final ServiceManager INSTANCE = new ServiceManager();
    private static final String CONFIG_FILE_NAME = "user_config.yaml";
    private static final String PROCESS_NAME = "erk_service";
    private static final AppInstance appInstance = AppInstance.getInstance();
    private static final UserConfig config = appInstance.getUserConfig();

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
        config.init(Path.of(configPath).resolve(CONFIG_FILE_NAME).toString());

        // RMQ 서버 연결 및 RMQ Consumer 등록
        RmqManager rmqManager = RmqManager.getInstance();
        rmqManager.addRmqModule(config.getRmqIncomingQueueApi(), new RmqStreamModule(config.getRmqHost(), config.getRmqUser(), config.getRmqPassword(), config.getRmqPort()), config.getRmqIncomingQueueApi(),
                () -> log.info("RMQ API QUEUE Connected"),
                () -> log.warn("RMQ API QUEUE Disconnected"));
        rmqManager.connectRmqModule(config.getRmqIncomingQueueApi(), ErkApiMsgRmqConsumer::consumeApiMessage);

        rmqManager.addRmqModule(config.getRmqIncomingQueueSubsystem(), new RmqStreamModule(config.getRmqHost(), config.getRmqUser(), config.getRmqPassword(), config.getRmqPort()), config.getRmqIncomingQueueSubsystem(),
                () -> log.info("RMQ SUBSYSTEM QUEUE Connected"),
                () -> log.warn("RMQ SUBSYSTEM QUEUE Disconnected"));
        rmqManager.connectRmqModule(config.getRmqIncomingQueueSubsystem(), ErkApiMsgRmqConsumer::consumeSubsystemApiMessage);

        IntervalTaskManager.startAll();
        DBManager.getInstance().start();

        // Prometheus 연동
        if (config.isPrometheusActivate()) {
            port(config.getPrometheusPort());
            get(config.getPrometheusMetricsPath(), (req, res) -> PrometheusManager.getInstance().registry.scrape());
        }

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
        DBManager.getInstance().stop();
        RmqManager.getInstance().stop();
        IntervalTaskManager.stopAll();
        isQuit = true;
    }

}
