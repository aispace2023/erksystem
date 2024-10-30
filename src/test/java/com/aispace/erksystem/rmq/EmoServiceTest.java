package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.module.DaoMockManager;
import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.aispace.erksystem.rmq.module.RmqMsgBuilder.*;
import static com.aispace.erksystem.rmq.module.RmqSimBase.handleMessage;
import static com.aispace.erksystem.rmq.module.RmqSimBase.lastSendRmqMsg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * @author kangmoo Heo
 */
class EmoServiceTest {
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
    @DisplayName("EmoService 생성 / 삭제 테스트")
    void emoServiceTest() {

        // 생성 테스트
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis()));

        new Thread(() -> handleMessage(getEmoServiceStartRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis(), ServiceType_e.ServiceType_speech))).start();
        await().atMost(1_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.ERKENGINECREATERQ));
        assertThat(lastSendRmqMsg.getErkEngineCreateRQ().getServiceType()).isEqualTo(ServiceType_e.ServiceType_speech);

        ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder()
                .setEngineType(EngineType_e.EngineType_speech)
                .setEngineCondition(EngineCondition_e.EngineCondition_available)
                .setIpAddr("127.0.0.1")
                .setReceiveQueueName("RECEIVE_QUEUE")
                .setSendQueueName("SEND_QUEUE").build();


        handleMessage(getErkEngineCreateRP(lastSendRmqMsg.getErkEngineCreateRQ().getErkInterMsgHead().getTransactionId(), orgId, userId, System.currentTimeMillis(), ReturnCode_e.ReturnCode_ok,
                EngineType_e.EngineType_speech,
                engineInfo));

        await().atMost(6_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.EMOSERVICESTARTRP));
        assertThat(lastSendRmqMsg.getEmoServiceStartRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_ok);
        assertThat(lastSendRmqMsg.getEmoServiceStartRP().getSpeechEngineInfo()).isEqualTo(engineInfo);

        // 삭제 테스트
        new Thread(() -> handleMessage(getEmoServiceStopRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis(), ServiceType_e.ServiceType_speech, "", "", "RECV_02_000_000", "SEND_02_000_000", "", "", "", ""))).start();
        await().atMost(1_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.ERKENGINEDELETERQ));

        ErkEngineInfo_s emptyInfo = ErkEngineInfo_s.newBuilder().build();
        handleMessage(getErkEngineDeleteRP(lastSendRmqMsg.getErkEngineDeleteRQ().getErkInterMsgHead().getTransactionId(), orgId, userId, System.currentTimeMillis(), ReturnCode_e.ReturnCode_ok, emptyInfo, engineInfo, emptyInfo, emptyInfo, emptyInfo));

        await().atMost(1_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.EMOSERVICESTOPRP));
        assertThat(lastSendRmqMsg.getEmoServiceStopRP().getReturnCode()).isEqualTo(ReturnCode_e.ReturnCode_ok);
        assertThat(lastSendRmqMsg.getEmoServiceStopRP().getSpeechEngineInfo()).isEqualTo(engineInfo);
    }

    @Test
    @DisplayName("EmoService 생성 실패 테스트")
    void emoServiceFailTest() {
        // 생성 테스트
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, "22000101", 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, "22000101", 30, SexType_e.SexType_unknown, UserType_e.UserType_unknown, ServiceType_e.ServiceType_unknown));
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        handleMessage(getErkServiceConnRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis()));

        new Thread(() -> handleMessage(getEmoServiceStartRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgId, userId, System.currentTimeMillis(), ServiceType_e.ServiceType_speech))).start();
        await().atMost(1_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.ERKENGINECREATERQ));

        ErkEngineInfo_s engineInfo = ErkEngineInfo_s.newBuilder()
                .setEngineType(EngineType_e.EngineType_speech)
                .setEngineCondition(EngineCondition_e.EngineCondition_available)
                .setIpAddr("127.0.0.1")
                .setReceiveQueueName("RECEIVE_QUEUE")
                .setSendQueueName("SEND_QUEUE").build();

        handleMessage(getErkEngineCreateRP("", orgId, userId, System.currentTimeMillis(), ReturnCode_e.ReturnCode_ok, EngineType_e.EngineType_speech, engineInfo));

        await().atMost(6_000, TimeUnit.MILLISECONDS).until(() -> lastSendRmqMsg.getMsgCase().equals(ErkApiMsg.MsgCase.EMOSERVICESTARTRP));
        assertThat(lastSendRmqMsg.getEmoServiceStartRP().getReturnCode()).isNotEqualTo(ReturnCode_e.ReturnCode_ok);
    }
}
