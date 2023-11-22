package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Random;

@Slf4j
public class ServiceProviderDAO {
    private static final SessionFactory sessionFactory = DBManager.getInstance().getSessionFactory();

    private ServiceProviderDAO() {}

    public static boolean create(ServiceProvider serviceProvider) {
        int newId = 1 + new Random().nextInt(999999998);
        serviceProvider.setOrgId(newId);
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(serviceProvider);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("insert error", e);
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider read(int orgId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(ServiceProvider.class, orgId);
        } catch (Exception e) {
            log.warn("select error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider readbyName(String orgName) {
        Session session = sessionFactory.openSession();
        try {
            return session.bySimpleNaturalId(ServiceProvider.class).load(orgName);
        } catch (Exception e) {
            log.warn("select error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
    public static boolean update(ServiceProvider serviceProvider) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(serviceProvider);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("update error", e);
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean delete(long orgId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            boolean result = false;
            tx = session.beginTransaction();
            ServiceProvider serviceProvider = session.get(ServiceProvider.class, orgId);
            if (serviceProvider != null) {
                session.delete(serviceProvider);
                result = true;
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("delete error", e);
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
