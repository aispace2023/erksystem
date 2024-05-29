package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceType;
import com.erksystem.protobuf.api.ProviderType_e;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTest {
    private static final DBManager dbManager = DBManager.getInstance();
    private int orgId, userId;
    private String orgName = "provTest";
    private String userName = "userTest-" + new Random().nextInt(99999);
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
        if(proceed) serviceUserDelTest();
        if(proceed) serviceProviderDelTest();

        try {
            dbManager.stop();
            System.out.println("DBManager stopped.");
        } catch (Exception e) {
            System.out.println("stopTest Fail");
        }
    }

    void serviceProviderTest() {
        this.proceed = false;
        ServiceProvider provider = new ServiceProvider();
        provider.setOrgName(this.orgName);
        provider.setOrgPwd("99999");
        provider.setProviderType(ProviderType_e.ProviderType_education.getNumber());
        provider.setServiceDuration("20241231");
        provider.setUserNumber(5);
        provider.setServiceType(ServiceType.speech.getValue());

        ServiceProviderDAO.create(provider);
        assertNotEquals(0, provider.getOrgId());
        this.orgId = provider.getOrgId();
        ServiceProvider read = ServiceProviderDAO.read(this.orgId);
        assertNotNull(read);
        System.out.println("provById = [" + read + "]");
        //ServiceProviderDAO.delete(org_id);
        this.proceed = true;
    }

    void serviceProviderReadTest() {
        this.proceed = false;
        ServiceProvider provider = ServiceProviderDAO.read(this.orgName);
        assertNotNull(provider);
        System.out.println("provByName = [" + provider +"]");
        this.proceed = true;
    }

    void serviceProviderDelTest() {
        this.proceed = false;
        System.out.println("provider Delete Result = " +
                ServiceProviderDAO.delete(this.orgId));
        this.proceed = true;
    }

    void serviceUserTest() {
        this.proceed = false;
        ServiceUser user = new ServiceUser();
        user.setOrgId(this.orgId);
        user.setUserName(this.userName);
        user.setUserPwd("pwdTest");
        user.setServiceDuration("20240630");
        user.setAge(15);
        user.setServiceType(ServiceType.physiology_face.getValue());
        user.setServiceStatus(0);
        user.setServiceNumber(1);
        assertTrue(ServiceUserDAO.create(user));
        this.userId = user.getUserId();
        ServiceUser read = ServiceUserDAO.read(this.userId, this.orgId);
        assertNotNull(read);
        System.out.println(("userById = [" + read + "]"));
        this.proceed = true;
    }

    void serviceUserReadTest() {
        this.proceed = false;
        ServiceUser user = ServiceUserDAO.read(this.userName);
        assertNotNull(user);
        System.out.println("userByName = [" + user + "]");
        this.proceed = true;
    }

    void serviceUserDelTest() {
        this.proceed = false;
        System.out.println("user Delete Result = " +
                ServiceUserDAO.delete(this.userName));
        this.proceed = true;
    }
}
