package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.module.DaoMockManager;
import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.aispace.erksystem.service.AppInstance;
import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.erksystem.protobuf.api.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static com.aispace.erksystem.rmq.module.RmqMsgBuilder.*;
import static com.aispace.erksystem.rmq.module.RmqSimBase.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kangmoo Heo
 */
class ServiceProviderTest {
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
    @DisplayName("ServiceProvider 생성 / 조회 / 삭제 테스트")
    void serviceProviderTest() {
        ProviderType_e providerType = ProviderType_e.ProviderType_education;
        String serviceDuration = "22000101";
        int userNumber = 7777;
        ServiceType_e serviceType = ServiceType_e.ServiceType_physiology_speech;

        // 생성 테스트
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, providerType, serviceDuration, userNumber, serviceType));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ADDSERVICEPROVIDERINFORP);
        assertThat(lastSendRmqMsg.getAddServiceProviderInfoRP().getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();
        assertThat(ServiceProviderDAO.read(orgId)).isNotNull();

        // 조회 테스트
        handleMessage(getDisServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.DISSERVICEPROVIDERINFORP);
        DisServiceProviderInfoRP_m disServiceProviderInfoRP = lastSendRmqMsg.getDisServiceProviderInfoRP();
        assertThat(disServiceProviderInfoRP.getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        assertThat(disServiceProviderInfoRP.getOrgName()).isEqualTo(orgName);
        assertThat(disServiceProviderInfoRP.getOrgId()).isEqualTo(orgId);
        assertThat(disServiceProviderInfoRP.getOrgPwd()).isEqualTo(orgPwd);
        assertThat(disServiceProviderInfoRP.getProviderType()).isEqualTo(providerType);
        assertThat(disServiceProviderInfoRP.getServiceDuration()).isEqualTo(serviceDuration);
        assertThat(disServiceProviderInfoRP.getUserNumber()).isEqualTo(userNumber);
        assertThat(disServiceProviderInfoRP.getServiceType()).isEqualTo(serviceType);

        // TODO 수정 테스트 추가
/*
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

        // 삭제 테스트
        handleMessage(getDelServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.DELSERVICEPROVIDERINFORP);
        assertThat(lastSendRmqMsg.getDelServiceProviderInfoRP().getResultType()).isEqualTo(OrgProfileResult_e.OrgProfileResult_ok);
        assertThat(ServiceProviderDAO.read(orgId)).isNull();
    }
}
