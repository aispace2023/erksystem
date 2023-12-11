package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceUser;
import com.aispace.erksystem.service.database.type.ServiceUserId;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLDataException;

@Slf4j
public class ServiceUserDAO {
    private static final SessionFactory sessionFactory = DBManager.getInstance().getSessionFactory();

    public static boolean create(ServiceUser serviceUser) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT COALESCE(MAX(user_id), 0) AS id_max FROM service_user_tbl");
            int newId = (Integer)query.getSingleResult() + 1;   // query result 가 없거나 복수 개인 경우 exception
            if (newId < 1 || newId > 999) throw new SQLDataException("can't retrieve max user_id");
            serviceUser.setUserId(newId);
            session.save(serviceUser);
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

    public static ServiceUser read(Integer userId, Integer orgId) {
        Session session = sessionFactory.openSession();
        try {
            // key object 정의한 경우 ServiceUser class 정의를 맞추어야 함.
            ServiceUser user = new ServiceUser();
            user.setOrgId(orgId);
            user.setUserId(userId);
            return session.get(ServiceUser.class, user);
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
            throw e;
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
