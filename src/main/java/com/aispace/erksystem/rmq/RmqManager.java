package com.aispace.erksystem.rmq;

import com.aispace.erksystem.rmq.module.RmqModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * RabbitMQ 모듈들을 제어하고 관리하는 매니저 클래스.
 * <p>
 * Created by Ai_Space
 */
@Getter
@Slf4j
public class RmqManager {
    private RmqModule rmqModule;

    private RmqManager() {
    }

    private static class SingletonInstance {
        private static final RmqManager INSTANCE = new RmqManager();
    }

    public static RmqManager getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void init(String host, String userName, String password, Integer port, int bufferCount) throws IOException, TimeoutException {
        if (this.rmqModule != null) {
            log.warn("RmqModule is already initialized.");
            return;
        }
        this.rmqModule = new RmqModule(host, userName, password, port, bufferCount);
        this.rmqModule.connect();
    }

    public void destroy() {
        if (this.rmqModule == null) {
            return;
        }
        this.rmqModule.close();
        this.rmqModule = null;
    }

    public void sendMsg(String queueName, byte[] message) {
        rmqModule.sendMessage(queueName, message);
    }

    public void sendMsg(String queueName, String message) {
        this.sendMsg(queueName, message.getBytes());
    }

    public void addConsumer(String queueName, Consumer<byte[]> consumer) throws IOException {
        try {
            rmqModule.queueDeclare(queueName);
        } catch (Exception e){
            // Do Nothing
        }
        rmqModule.registerConsumer(queueName, consumer);
    }
}