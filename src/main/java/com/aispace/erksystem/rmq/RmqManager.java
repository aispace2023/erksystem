package com.aispace.erksystem.rmq;

import com.aispace.erksystem.rmq.module.RmqStreamModule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * RabbitMQ 모듈들을 제어하고 관리하는 매니저 클래스.
 *
 * Created by Ai_Space
 */
@Slf4j
public class RmqManager {

    private static final RmqManager INSTANCE = new RmqManager();
    private final ConcurrentHashMap<String, RmqInfo> rmqInfos = new ConcurrentHashMap<>();

    private RmqManager() {
    }

    public static RmqManager getInstance() {
        return INSTANCE;
    }

    /**
     * 새로운 RabbitMQ 모듈을 매니저에 추가한다.
     *
     * @param key            RMQ 모듈에 대한 고유 식별자.
     * @param rmqModule      RMQ 모듈의 인스턴스.
     * @param consumerQueue  Consumer Queue 이름.
     * @param onConnected    연결됐을 때 실행될 콜백.
     * @param onDisconnected 연결이 끊겼을 때 실행될 콜백.
     */
    public void addRmqModule(String key, RmqStreamModule rmqModule, String consumerQueue, Runnable onConnected, Runnable onDisconnected) {
        if (rmqInfos.putIfAbsent(key, new RmqInfo(rmqModule, consumerQueue, onConnected, onDisconnected)) != null) {
            log.warn("The key already exists in the map. [{}]", key);
        }
    }

    public void connectRmqModule(String key, Consumer<byte[]> rmqConsumer) {
        RmqInfo rmqInfo = rmqInfos.get(key);
        if (rmqInfo == null) {
            throw new IllegalStateException("RMQ Module is not exist. [" + key + "]");
        }

        RmqStreamModule rmqModule = rmqInfo.getRmqModule();
        String consumerQueue = rmqInfo.getConsumerQueue();
        Runnable onConnected = rmqInfo.getOnConnected();
        Runnable onDisconnected = rmqInfo.getOnDisconnected();

        rmqModule.connectWithAsyncRetry(() -> {
            try {
                onConnected.run();
            } catch (Exception e) {
                log.warn("Err Occurs onConnected", e);
            }

            try {
                rmqModule.queueDeclare(consumerQueue);
                rmqModule.registerByteConsumer(consumerQueue, msg -> {
                    try {
                        rmqConsumer.accept(msg);
                    } catch (Exception e) {
                        log.warn("Err Occurs while handling RMQ message ", e);
                    }
                });
            } catch (IOException e) {
                log.warn("Fail to declare queue. [" + consumerQueue + "]");
            }
        }, () -> {
            try {
                onDisconnected.run();
            } catch (Exception e) {
                log.warn("Err Occurs onDisconnected", e);
            }
        });
    }

    /**
     * 지정된 RabbitMQ 모듈을 제거하고 RmqModule을 닫는다.
     *
     * @param key 제거될 RMQ 모듈의 고유 식별자.
     */
    public RmqInfo removeRmqModule(String key) {
        RmqInfo rmqInfo = rmqInfos.remove(key);
        if (rmqInfo != null) {
            try {
                rmqInfo.getRmqModule().close();
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        }
        return rmqInfo;
    }

    /**
     * 모든 실행 중인 RabbitMQ 모듈을 멈추고 매니저를 초기화한다.
     */

    public void stop() {
        rmqInfos.keySet().forEach(this::removeRmqModule);
    }

    public Optional<RmqStreamModule> getRmqModule(String key) {
        return Optional.ofNullable(rmqInfos.get(key)).map(RmqInfo::getRmqModule);
    }

    public Map<String, RmqInfo> getCloneRmqModules() {
        return new HashMap<>(rmqInfos);
    }

    /**
     * RabbitMQ 모듈에 대한 상세 정보를 캡슐화하는 내부 클래스.
     */
    @Data
    public static class RmqInfo {
        final RmqStreamModule rmqModule;
        final String consumerQueue;
        final Runnable onConnected;
        final Runnable onDisconnected;
    }
}