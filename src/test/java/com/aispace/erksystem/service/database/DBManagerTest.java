package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DBManagerTest {
    private static final DBManager dbManager = DBManager.getInstance();
    private int orgId;

    @BeforeAll
    static void initTest() {
        try {
            dbManager.start();
            log.info("DBManager started.");
        } catch (Exception e) {
            log.warn("initTest Fail");
        }
    }

//    @Test
//    void serviceProviderReadTest() {
//        ServiceProvider provider = ServiceProviderDAO.readbyName("유엔젤-테스트");
//        log.info("provider = {}", provider);
//    }

    @Test
    void serviceUserReadTest() {
        ServiceUser user = ServiceUserDAO.read(781206992, 1);
        log.info("user = {}", user);
    }

//    @Test
//    void serviceProviderTest() {
//        ServiceProvider provider = new ServiceProvider();
//        provider.setServiceType(ServiceType.speech.name());
//        provider.setOrgName("유엔젤-테스트");
//        provider.setOrgPwd("99999");
//        provider.setUserNumber(5);
//        provider.setServiceDuration("20241231");
//
//        ServiceProviderDAO.create(provider);
//        assertNotEquals(0, provider.getOrgId());
//        ServiceProvider read = ServiceProviderDAO.read(orgId);
//        assertNotNull(read);
//        log.info("serviceProvider [{}]", read);
//
//        //ServiceProviderDAO.delete(org_id);
//    }
//
//    @Test
//    void serviceUserTest() {
//        ServiceUser user = new ServiceUser();
//        user.setOrgId(1);
//        user.setUserName("userTest");
//        user.setUserPwd("pwdTest");
//        user.setAge(15);
//        user.setServiceDuration("20240630");
//        user.setServiceType(ServiceType.physiology_face.name());
//        long userId = ServiceUserDAO.create(user);
//        ServiceUser read = ServiceUserDAO.read(userId, orgId);
//        assertNotNull(read);
//        log.info("serviceUser [{}]", read);
//    }

//    @AfterAll
//    static void stopTest() {
//        try {
//            dbManager.stop();
//            log.info("DBManager stopped.");
//        } catch (Exception e) {
//            log.warn("stopTest Fail");
//        }
//    }
}
