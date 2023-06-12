package com.aispace.erksystem.rmq.module;

import com.aispace.rmq.module.RmqService;
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

        // RMQ 연결
        try (RmqService rmqService = new RmqService(HOST, USER, PW)) {
            // RMQ Queue 생성
            rmqService.queueDeclare(QUEUE_NAME);
        }


        new Thread(() -> {
            try {
                // RMQ 연결
                RmqService consumer = new RmqService(HOST, USER, PW);

                // Consumer 등록
                consumer.registerStringConsumer(QUEUE_NAME, (message) -> log.debug("Consumed Message : {}", message));
                Thread.sleep(1000);
                consumer.close();
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        }, "Consumer").start();


        new Thread(() -> {
            try {
                // RMQ 연결
                RmqService producer = new RmqService(HOST, USER, PW);

                // Message 전송
                producer.sendMessage(QUEUE_NAME, "TEST_MESSAGE");
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        }, "Producer").start();

        Thread.sleep(1000);


    }
}
