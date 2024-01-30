package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceType;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTest {
    private static final DBManager dbManager = DBManager.getInstance();
    private int orgId, userId;
    boolean proceed = true;

    @Test
    void dbTest() {
        try {
            dbManager.start();
            System.out.println("DBManager started.");
        } catch (Exception e) {
            System.out.println("initTest Fail");
        }

        if(proceed) serviceProviderTest();
        if(proceed) serviceProviderReadTest();
        if(proceed) serviceUserTest();
        if(proceed) serviceUserReadTest();

        try {
            dbManager.stop();
            System.out.println("DBManager stopped.");
        } catch (Exception e) {
            System.out.println("stopTest Fail");
        }
    }

    void serviceProviderReadTest() {
        this.proceed = false;
        ServiceProvider provider = ServiceProviderDAO.read("유엔젤-테스트");
        System.out.println("provider = " + provider);
        this.proceed = true;
    }

    void serviceUserReadTest() {
        this.proceed = false;
        ServiceUser user = ServiceUserDAO.read(this.orgId, 1);
        System.out.println("user = " + user);
        this.proceed = true;
    }

    void serviceProviderTest() {
        this.proceed = false;
        ServiceProvider provider = new ServiceProvider();
        provider.setServiceType(ServiceType.speech.name());
        provider.setOrgName("테스트-" + new Random().nextInt(99999));
        provider.setOrgPwd("99999");
        provider.setUserNumber(5);
        provider.setServiceDuration("20241231");

        ServiceProviderDAO.create(provider);
        assertNotEquals(0, provider.getOrgId());
        this.orgId = provider.getOrgId();
        ServiceProvider read = ServiceProviderDAO.read(this.orgId);
        assertNotNull(read);
        System.out.println("serviceProvider [" + read + "]");
        //ServiceProviderDAO.delete(org_id);
        this.proceed = true;
    }

    void serviceUserTest() {
        this.proceed = false;
        ServiceUser user = new ServiceUser();
        user.setOrgId(this.orgId);
        user.setUserName("userTest" + new Random().nextInt(99999));
        user.setUserPwd("pwdTest");
        user.setAge(15);
        user.setServiceDuration("20240630");
        user.setServiceType(ServiceType.physiology_face.name());
        assertTrue(ServiceUserDAO.create(user));
        this.userId = user.getUserId();
        ServiceUser read = ServiceUserDAO.read(userId, orgId);
        assertNotNull(read);
        System.out.println(("serviceUser [" + read + "]"));
        this.proceed = true;
    }

}
