package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceUserId;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class ServiceUserDAO {
    private static final SessionFactory sessionFactory = DBManager.getInstance().getSessionFactory();

    public static long create(ServiceUser serviceUser) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(serviceUser);
            tx.commit();
            return serviceUser.getUserId();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("create error", e);
            return 0;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceUser read(long userId, long orgId) {
        Session session = sessionFactory.openSession();
        try {
            // TODO key object 정의한 경우 ServiceUser class 정의를 맞추어야 함.
            return session.get(ServiceUser.class, new ServiceUserId(userId, orgId));
        } catch (Exception e) {
            log.error("read error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static void update(ServiceUser serviceUser) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(serviceUser);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.warn("update error", e);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static void delete(int userId, int orgId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ServiceUser serviceUser = session.get(ServiceUser.class, new ServiceUserId(userId, orgId));
            if (serviceUser != null) {
                session.delete(serviceUser);
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
