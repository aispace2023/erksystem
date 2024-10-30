package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.module.DaoMockManager;
import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static com.aispace.erksystem.rmq.module.RmqMsgBuilder.*;
import static com.aispace.erksystem.rmq.module.RmqSimBase.handleMessage;
import static com.aispace.erksystem.rmq.module.RmqSimBase.lastSendRmqMsg;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kangmoo Heo
 */
class ErkServiceTest {
    private final UserConfig userConfig = AppInstance.getInstance().getUserConfig();
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();

    String orgName = "TEST_ORG_NAME";
    String orgPwd = "TEST_ORG_PWD";
    String userName = "TEST_USER_PWD";
    String userPwd = "TEST_USER_PWD";

    @BeforeAll
    static void beforeAll() throws IOException, NoSuchFieldException {
        RmqSimBase.init("src/test/resources/config");
    }

    @BeforeEach
    void setUp() {
        DaoMockManager.getInstance().setDbMock();
    }

    @AfterEach
    void tearDown() {
        DaoMockManager.getInstance().close();
        connectionManager.findConnectionInfo(o -> true).forEach(o -> connectionManager.deleteConnection(o.getOrgId(), o.getUserId()));
    }

    @Test
    @DisplayName("ErkServiceConnRQ 성공 테스트")
    void erkServiceConnRqTest() {
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis()));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ERKSERVICECONNRP);
        assertThat(lastSendRmqMsg.getErkServiceConnRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_ok);
    }

    @Test
    @DisplayName("ErkServiceConnRQ 실패 테스트")
    void erkServiceConnRqFailTest() {
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();

        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId + 1, userId, System.currentTimeMillis()));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ERKSERVICECONNRP);
        assertThat(lastSendRmqMsg.getErkServiceConnRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_nok_Org);

        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId + 1, System.currentTimeMillis()));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ERKSERVICECONNRP);
        assertThat(lastSendRmqMsg.getErkServiceConnRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_nok_User);
    }
}
