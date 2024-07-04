package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.service.database.ServiceProviderDAO;
import com.aispace.erksystem.service.database.ServiceUserDAO;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import com.aispace.erksystem.service.database.table.ServiceUser;
import lombok.Getter;
import lombok.Setter;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * @author kangmoo Heo
 */
@Getter
@Setter
public class DaoMockManager {

    private static MockedStatic<ServiceProviderDAO> serviceProviderDAOMockedStatic;
    private static MockedStatic<ServiceUserDAO> serviceUserDAOMockedStatic;

    Map<Integer, ServiceProvider> serviceProviders;
    Map<Integer, ServiceUser> serviceUsers;

    int orgId;
    int userId;

    private static final DaoMockManager INSTANCE = new DaoMockManager();

    private DaoMockManager() {
    }

    public static DaoMockManager getInstance() {
        return INSTANCE;
    }

    public void setDbMock() {
        orgId = 0;
        userId = 0;
        serviceProviders = new HashMap<>();
        serviceUsers = new HashMap<>();
        try {
            serviceProviderDAOMockedStatic = mockStatic(ServiceProviderDAO.class);
            serviceUserDAOMockedStatic = mockStatic(ServiceUserDAO.class);

            // Mock ServiceProviderDAO
            when(ServiceProviderDAO.create(any())).then(invocation -> {
                ServiceProvider serviceProvider = invocation.getArgument(0);
                serviceProvider.setOrgId(orgId);
                serviceProviders.put(orgId++, serviceProvider);
                return true;
            });
            when(ServiceProviderDAO.read(anyInt())).then(invocation -> serviceProviders.get((int) invocation.getArgument(0)));
            when(ServiceProviderDAO.read(anyString())).then(invocation -> {
                String orgName = invocation.getArgument(0);
                for (ServiceProvider serviceProvider : serviceProviders.values()) {
                    if (serviceProvider.getOrgName().equals(orgName)) {
                        return serviceProvider;
                    }
                }
                return null;
            });
            when(ServiceProviderDAO.update(any()))
                    .then(invocation -> {
                        ServiceProvider serviceProvider = invocation.getArgument(0);
                        if (serviceProviders.containsKey(serviceProvider.getOrgId())) {
                            serviceProviders.put(serviceProvider.getOrgId(), serviceProvider);
                            return true;
                        }
                        return false;
                    });
            when(ServiceProviderDAO.delete(anyInt())).then(invocation -> serviceProviders.remove((int) invocation.getArgument(0)) != null);

            // Mock ServiceUserDAO
            when(ServiceUserDAO.create(any()))
                    .then(invocation -> {
                        ServiceUser serviceUser = invocation.getArgument(0);
                        serviceUser.setUserId(userId);
                        serviceUsers.put(userId++, serviceUser);
                        return true;
                    });
            when(ServiceUserDAO.read(anyInt(), anyInt())).then(invocation -> {
                int userId = invocation.getArgument(0);
                int orgId = invocation.getArgument(1);
                for (ServiceUser serviceUser : serviceUsers.values()) {
                    if (serviceUser.getUserId() == userId && serviceUser.getOrgId() == orgId) {
                        return serviceUser;
                    }
                }
                return null;
            });
            when(ServiceUserDAO.read(anyString())).then(invocation -> {
                String userName = invocation.getArgument(0);
                for (ServiceUser serviceUser : serviceUsers.values()) {
                    if (serviceUser.getUserName().equals(userName)) {
                        return serviceUser;
                    }
                }
                return null;
            });
            when(ServiceUserDAO.update(any()))
                    .then(invocation -> {
                        ServiceUser serviceUser = invocation.getArgument(0);
                        if (serviceUsers.containsKey(serviceUser.getUserId())) {
                            serviceUsers.put(serviceUser.getUserId(), serviceUser);
                            return true;
                        }
                        return true;
                    });
            when(ServiceUserDAO.delete(any())).then(invocation -> {
                String userName = invocation.getArgument(0);
                for (ServiceUser serviceUser : serviceUsers.values()) {
                    if (serviceUser.getUserName().equals(userName)) {
                        return serviceUsers.remove(serviceUser.getUserId()) != null;
                    }
                }
                return false;
            });
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void close() {
        serviceProviderDAOMockedStatic.close();
        serviceUserDAOMockedStatic.close();
    }
}