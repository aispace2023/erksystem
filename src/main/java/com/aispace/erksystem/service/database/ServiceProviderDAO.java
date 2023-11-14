package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class ServiceProviderDAO {
    private static final SessionFactory sessionFactory = DBManager.getInstance().getSessionFactory();

    private ServiceProviderDAO() {}

    public static long create(ServiceProvider serviceProvider) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(serviceProvider);
            tx.commit();
            return serviceProvider.getOrgId();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
//            log.warn("create error", e);
//            return 0;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider read(long orgId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(ServiceProvider.class, orgId);
        } catch (Exception e) {
            log.warn("read error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static void update(ServiceProvider serviceProvider) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(serviceProvider);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("update error", e);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static void delete(long orgId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ServiceProvider serviceProvider = session.get(ServiceProvider.class, orgId);
            if (serviceProvider != null) {
                session.delete(serviceProvider);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("delete error", e);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
