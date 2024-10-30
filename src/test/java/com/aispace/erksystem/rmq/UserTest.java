package com.aispace.erksystem.rmq;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.module.DaoMockManager;
import com.aispace.erksystem.rmq.module.RmqSimBase;
import com.aispace.erksystem.service.AppInstance;
import com.aispace.erksystem.service.database.ServiceUserDAO;
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
class UserTest {
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
    @DisplayName("User 생성 / 조회 / 삭제 테스트")
    void userTest() {
        String serviceDuration = "22000101";
        int age = 30;
        SexType_e sexType = SexType_e.SexType_unknown;
        UserType_e userType = UserType_e.UserType_unknown;
        ServiceType_e serviceType = ServiceType_e.ServiceType_unknown;

        // 생성 테스트
        handleMessage(getAddServiceProviderInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, orgPwd, ProviderType_e.ProviderType_education, serviceDuration, 7777, ServiceType_e.ServiceType_physiology_speech));
        int orgId = lastSendRmqMsg.getAddServiceProviderInfoRP().getOrgId();

        handleMessage(getAddUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd, serviceDuration, age, sexType, userType, serviceType));

        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.ADDUSERINFORP);
        assertThat(lastSendRmqMsg.getAddUserInfoRP().getResultType()).isEqualTo(UserProfileResult_e.UserProfileResult_ok);
        int userId = lastSendRmqMsg.getAddUserInfoRP().getUserId();
        assertThat(ServiceUserDAO.read(userId, orgId)).isNotNull();
        assertThat(ServiceUserDAO.read(userId, orgId)).isEqualTo(ServiceUserDAO.read(userName));

        // 조회 테스트
        handleMessage(getDisUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd));
        DisUserInfoRP_m disUserInfoRP = lastSendRmqMsg.getDisUserInfoRP();
        assertThat(disUserInfoRP.getResultType()).isEqualTo(UserProfileResult_e.UserProfileResult_ok);
        assertThat(disUserInfoRP.getUserId()).isEqualTo(userId);
        assertThat(disUserInfoRP.getUserName()).isEqualTo(userName);
        assertThat(disUserInfoRP.getServiceDuration()).isEqualTo(serviceDuration);
        assertThat(disUserInfoRP.getAge()).isEqualTo(age);
        assertThat(disUserInfoRP.getSex()).isEqualTo(sexType);
        assertThat(disUserInfoRP.getUserType()).isEqualTo(userType);
        assertThat(disUserInfoRP.getServiceType()).isEqualTo(serviceType);

        // TODO 수정 테스트 추가

        // 삭제 테스트
        handleMessage(getDelUserInfoRQ(getQueueInfo(userConfig.getRmqIncomingQueueApi(), "API_QUEUE"), orgName, userName, userPwd));
        assertThat(lastSendRmqMsg.getMsgCase()).isEqualTo(ErkApiMsg.MsgCase.DELUSERINFORP);
        assertThat(lastSendRmqMsg.getDelUserInfoRP().getResultType()).isEqualTo(UserProfileResult_e.UserProfileResult_ok);
        assertThat(ServiceUserDAO.read(userId, orgId)).isNull();
    }
}
