package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class ServiceProviderDAO {
    private final DBManager dbManager = DBManager.getInstance();

    public ServiceProviderDAO() {
        // do nothing
    }

    public boolean create(ServiceProvider serviceProvider) {
        SessionFactory factory = dbManager.getSessionFactory();
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(serviceProvider);
            log.info("Created serviceProvider {}", serviceProvider);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Create error", e);
            return false;
        }
    }

    public ServiceProvider read(int id) {
        SessionFactory factory = dbManager.getSessionFactory();
        try (Session session = factory.openSession()) {
            return session.get(ServiceProvider.class, id);
        } catch (Exception e) {
            log.error("Read error", e);
            return null;
        }
    }

    public boolean update(ServiceProvider serviceProvider) {
        SessionFactory factory = dbManager.getSessionFactory();
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(serviceProvider);
            log.info("Updated serviceProvider: {}", serviceProvider);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Update error", e);
            return false;
        }
    }

    public boolean delete(int orgId) {
        SessionFactory factory = dbManager.getSessionFactory();
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            ServiceProvider serviceProvider = session.get(ServiceProvider.class, orgId);
            if (serviceProvider != null) {
                session.delete(serviceProvider);
                log.info("Deleted serviceProvider with orgId: {}", orgId);
                transaction.commit();
                return true;
            } else {
                log.warn("ServiceProvider not found with ID: {}", orgId);
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Delete error", e);
            return false;
        }
    }

}
