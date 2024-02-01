package com.aispace.erksystem.rmq;

import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.erksystem.protobuf.api.ServiceType_e;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.aispace.erksystem.rmq.module.RmqMsgBuilder.*;
import static com.aispace.erksystem.rmq.module.RmqSimBase.handleMessage;

/**
 * @author kangmoo Heo
 */
class ServiceProviderTest {
    @Test
    @DisplayName("ServiceProvider 생성 / 삭제 테스트")
    void serviceProviderTest() throws IOException, NoSuchFieldException {
        RmqSimBase.init("src/test/resources/config");

        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo("ERK_API_QUEUE", "API_QUEUE"),
                "TEST_ORG_NAME", "TEST", "20330101", 7777, ServiceType_e.ServiceType_physiology_speech));

        handleMessage(getDelServiceProviderInfoRQ(getQueueInfo("ERK_API_QUEUE", "API_QUEUE"),
                "TEST_ORG_NAME", "TEST"));
    }
}
