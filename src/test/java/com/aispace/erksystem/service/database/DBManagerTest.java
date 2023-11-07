package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.type.ServiceType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class DBManagerTest {
    private static final DBManager dbManager = DBManager.getInstance();

    @BeforeAll
    static void initTest() {
        try {
            dbManager.start();
            log.info("DBManager started.");
        } catch (Exception e) {
            log.warn("initTest Fail");
        }
    }

    @Test
    void daoTest() {
        new ServiceProviderDAO().delete(1234);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setOrgId(1234);
        serviceProvider.setServiceType(ServiceType.speech);
        serviceProvider.setOrgName("유엔젤-테스트");
        serviceProvider.setOrgPwd("99999");
        serviceProvider.setUserNumber(5);
        serviceProvider.setServiceDuration("20241231");

        assertEquals(Boolean.TRUE, new ServiceProviderDAO().create(serviceProvider));
        assertNotNull(new ServiceProviderDAO().read(1234));
    }

    @AfterAll
    static void stopTest() {
        try {
            dbManager.stop();
            log.info("DBManager stopped.");
        } catch (Exception e) {
            log.warn("stopTest Fail");
        }
    }
}
