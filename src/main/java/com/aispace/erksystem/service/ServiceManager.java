package com.aispace.erksystem.service;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.module.RmqServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ServiceManager {
    private static final ServiceManager INSTANCE = new ServiceManager();
    private static final String CONFIG_FILE_NAME = "user_config.yaml";
    private static final AppInstance appInstance = AppInstance.getInstance();
    private static final UserConfig config = appInstance.getConfig();
    private RmqServer rmqServer;
    private boolean isQuit = false;

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        return INSTANCE;
    }

    public ServiceManager init(String configPath) {
        appInstance.setConfigPath(configPath);
        String userConfigFile = Path.of(configPath).resolve(CONFIG_FILE_NAME).toString();
        try {
            config.init(userConfigFile);
        } catch (IOException | NoSuchFieldException e) {
            log.warn("Fail to setting Config [{}]", userConfigFile, e);
            throw new RuntimeException(e);
        }
        return this;
    }

    public void startService() {
        try {
            rmqServer = new RmqServer(config.getRmqHost(), config.getRmqUser(), config.getRmqPassword());
            rmqServer.connect(config.getRmqConsumerCount());
            rmqServer.addConsumer(config.getIncomingQueue());
        } catch (Exception e){
            new RuntimeException("Start Service fail");
        }
    }

    public void stopService() {
        this.isQuit = true;
        this.rmqServer.disconnect();
    }

    public void loop() {
        this.startService();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("Process is about to quit");
            this.stopService();
        }));

        while (!isQuit) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
