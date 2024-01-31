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

    private ServiceUserDAO() {}

    public static boolean create(ServiceUser su) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Integer> query = session.createNativeQuery("SELECT COALESCE(MAX(user_id), 0) AS id_max FROM service_user_tbl");
            int newId = query.getSingleResult() + 1;   // query result 가 없거나 복수 개인 경우 exception
            if (newId < 1 || newId > 999) throw new SQLDataException("can't retrieve max user_id");
            su.setUserId(newId);
            session.save(su);
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

    public static ServiceUser read(int userId, int orgId) {
        Session session = sessionFactory.openSession();
        try {
            // key object 정의한 경우 ServiceUser class 정의를 맞추어야 함.
            ServiceUser su = new ServiceUser();
            su.setOrgId(orgId);
            su.setUserId(userId);
            return session.get(ServiceUser.class, su);
        } catch (Exception e) {
            log.error("read error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceUser read(int orgId, String userName) {
        Session session = sessionFactory.openSession();
        try {
            String sql = "SELECT org_id, user_id, user_name, user_pwd " +
                    "FROM service_user_tbl WHERE org_id=? AND user_name=?";
            //위치기반 파라미터 사용
            Query<ServiceUser> query = session.createNativeQuery(sql, ServiceUser.class)
                    .setParameter(1, orgId)
                    .setParameter(2, userName);
            return query.getSingleResult();   // query result 가 없거나 복수 개인 경우 exception
        } catch (Exception e) {
            log.error("read error", e);
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean update(ServiceUser su) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(su);
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

    public static boolean delete(int orgId, int userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            boolean result = false;
            tx = session.beginTransaction();
            ServiceUser su = session.get(ServiceUser.class, new ServiceUserId(userId, orgId));
            if (su != null) {
                session.delete(su);
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
