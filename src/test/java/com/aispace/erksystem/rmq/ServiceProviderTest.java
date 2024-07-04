package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.module.DaoMockManager;
import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.aispace.erksystem.service.AppInstance;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.erksystem.protobuf.api.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.aispace.erksystem.rmq.module.RmqMsgBuilder.*;
import static com.aispace.erksystem.rmq.module.RmqSimBase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * @author kangmoo Heo
 */
class ServiceProviderTest {
    private UserConfig userConfig = AppInstance.getInstance().getUserConfig();

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
    }


    @Test
    @DisplayName("ServiceProvider 생성 / 수정 / 삭제 테스트")
    void serviceProviderTest() {
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ADDSERVICEPROVIDERINFORP);
        assertThat(lastSendRmqMsg.getAddServiceProviderInfoRP().getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        assertThat(ServiceProviderDAO.read(orgId)).isNotNull();

/*        // TODO 수정 테스트
        String newOrgPwd = "NEW_" + orgPwd;
        int newUserNumber = 8888;
        handleMessage(getUpdateServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName,
                orgPwd, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech,
                newOrgPwd, "22000102", newUserNumber, ServiceType_e.ServiceType_speech));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.UPDATESERVICEPROVIDERINFORP);
        assertThat(lastSendRmqMsg.getUpdateServiceProviderInfoRP().getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        ServiceProvider serviceProvider = ServiceProviderDAO.read(orgId);
        assertThat(serviceProvider).isNotNull();
        assertThat(serviceProvider.getOrgPwd()).isEqualTo(newOrgPwd);
        assertThat(serviceProvider.getUserNumber()).isEqualTo(newUserNumber);
        assertThat(serviceProvider.getServiceType()).isEqualTo(ServiceType_e.ServiceType_speech.getNumber());*/

        handleMessage(getDelServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.DELSERVICEPROVIDERINFORP);
        assertThat(lastSendRmqMsg.getDelServiceProviderInfoRP().getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        assertThat(ServiceProviderDAO.read(orgId)).isNull();
    }

    @Test
    @DisplayName("User 생성 / 삭제 테스트")
    void userTest() {
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();

        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ADDUSERINFORP);
        assertThat(lastSendRmqMsg.getAddUserInfoRP().getResultType()).isEqualTo(UserProfileResult_e.UserProfileResult_ok);
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        assertThat(ServiceUserDAO.read(userId, orgId)).isNotNull();
        assertThat(ServiceUserDAO.read(userId, orgId)).isEqualTo(ServiceUserDAO.read(userName));

        handleMessage(getDelUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.DELUSERINFORP);
        assertThat(lastSendRmqMsg.getDelUserInfoRP().getResultType()).isEqualTo(UserProfileResult_e.UserProfileResult_ok);
        assertThat(ServiceUserDAO.read(userId, orgId)).isNull();
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
        assertThat(lastSendRmqMsg.getErkServiceConnRP().getReturnCode()).isNotEqualTo(ReturnCode_e.ReturnCode_ok);

        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId + 1, System.currentTimeMillis()));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ERKSERVICECONNRP);
        assertThat(lastSendRmqMsg.getErkServiceConnRP().getReturnCode()).isNotEqualTo(ReturnCode_e.ReturnCode_ok);
    }

    @Test
    @DisplayName("EmoServiceStartRQ 테스트")
    void emoServiceStartRqTest() {
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis()));

        handleMessage(getEmoServiceStartRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis(), ServiceType_e.ServiceType_speech));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ERKENGINECREATERQ);
        assertThat(lastSendRmqMsg.getErkEngineCreateRQ().getServiceType()).isEqualTo(ServiceType_e.ServiceType_speech);

        ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder()
                .setEngineType(EngineType_e.EngineType_speech)
                .setEngineCondition(EngineCondition_e.EngineCondition_available)
                .setIpAddr("127.0.0.1")
                .setReceiveQueueName("RECEIVE_QUEUE")
                .setSendQueueName("SEND_QUEUE").build();

        handleMessage(getErkEngineCreateRP(orgId, userId, System.currentTimeMillis(), ReturnCode_e.ReturnCode_ok,
                EngineType_e.EngineType_speech,
                engineInfo));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.EMOSERVICESTARTRP);
        assertThat(lastSendRmqMsg.getEmoServiceStartRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_ok);
        assertThat(lastSendRmqMsg.getEmoServiceStartRP().getSpeechEngineInfo()).isEqualTo(engineInfo);
    }
}
