package com.aispace.erksystem.rmq;

import com.aispace.erksystem.rmq.module.RmqModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * RabbitMQ 모듈들을 제어하고 관리하는 매니저 클래스.
 * <p>
 * Created by Ai_Space
 */
@Slf4j
public class RmqManager {
    private static final Map<String, RmqModule> rmqModules = new ConcurrentHashMap<>();

    private RmqManager() {
    }

    private static class SingletonInstance {
        private static final RmqManager INSTANCE = new RmqManager();
    }

    public static RmqManager getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void addRmqModule(String key, RmqModule rmqModule) {
        if (rmqModules.putIfAbsent(key, rmqModule) != null) {
            throw new IllegalArgumentException("Already Exists key [" + key + "]");
        }
    }

    public void addRmqModule(String key, String host, String userName, String password, int port, String targetQueue, Consumer<byte[]> consumer) {
        RmqModule rmqModule = RmqModule.builder(host, userName, password).setPort(port).build();
        rmqModule.setOnConnected(() -> {
            log.info("RMQ Connected [{}]", targetQueue);
            try {
                rmqModule.queueDeclare(targetQueue);
                rmqModule.registerByteConsumer(targetQueue, msg -> {
                    try {
                        consumer.accept(msg);
                    } catch (Exception e) {
                        log.warn("Err Occurs while handling RMQ message ", e);
                    }
                });
            } catch (IOException e) {
                log.warn("Fail to declare queue. [" + targetQueue + "]");
            }
        });
        rmqModule.setOnDisconnected(() -> log.info("RMQ DisConnected [{}]", targetQueue));
        addRmqModule(key, rmqModule);
    }

    public void connectAll() {
        for (RmqModule rmqModule : rmqModules.values()) {
            rmqModule.connectWithAsyncRetry();
        }
    }

    public void disconnectAll() {
        for (RmqModule rmqModule : rmqModules.values()) {
            rmqModule.close();
        }
    }

    public Optional<RmqModule> getRmqModule(String key) {
        return Optional.ofNullable(rmqModules.get(key));
    }

    public RmqModule removeRmqModule(String key) {
        return rmqModules.remove(key);
    }

    public Map<String, RmqModule> getRmqModules() {
        synchronized (rmqModules) {
            return new HashMap<>(rmqModules);
        }
    }
}