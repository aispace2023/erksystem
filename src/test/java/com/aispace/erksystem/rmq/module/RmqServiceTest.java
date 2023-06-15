package com.aispace.erksystem.rmq.module;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqServiceTest {

    private static final String QUEUE_NAME = "TEST_QUEUE";
    private static final String HOST = "192.168.7.33";
    private static final String USER = "acs";
    private static final String PW = "acs.123";

    @Test
    void rmqTest() throws Exception {

        // RMQ 연결 및 Queue 생성
        try (RmqService rmqService = new RmqService(HOST, USER, PW)) {
            rmqService.connect();
            rmqService.queueDeclare(QUEUE_NAME);
        }

        // RMQ 연결 및 메시지 핸들러 등록
        new Thread(() -> {
            try (RmqService consumer = new RmqService(HOST, USER, PW)){
                consumer.connect();
                consumer.registerStringConsumer(QUEUE_NAME, (message) -> log.debug("Consumed Message : {}", message));
                Thread.sleep(1000);
                consumer.close();
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        }, "Consumer").start();


        // RMQ 연결 및 메시지 메시지 전송
        new Thread(() -> {
            try (RmqService producer = new RmqService(HOST, USER, PW)){
                producer.connect();
                producer.sendMessage(QUEUE_NAME, "TEST_MESSAGE");
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        }, "Producer").start();

        Thread.sleep(1000);
    }
}
