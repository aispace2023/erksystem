package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class ServiceProviderDAO {
    private final DBManager dbManager = DBManager.getInstance();

    public ServiceProviderDAO() {
        // do nothing
    }
    public boolean create(ServiceProvider serviceProvider) {
        SessionFactory factory = dbManager.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.save(serviceProvider);
            log.info("create DATA [{}]", serviceProvider);
            return true;
        } catch (Exception e) {
            log.error("create error", e);
            return false;
        }
    }
}
