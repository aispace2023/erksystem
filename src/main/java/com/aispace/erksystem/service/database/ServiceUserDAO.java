package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceUser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

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
            NativeQuery<Integer> query = session.createNativeQuery(
                    "SELECT COALESCE(MAX(UserId), 0) AS id_max FROM SERVICE_USER_TBL WHERE OrgId=:orgId")
                    .setParameter("orgId", su.getOrgId())
                    .addScalar("id_max", StandardBasicTypes.INTEGER);
            int newId = query.getSingleResult() + 1;   // query result 가 없거나 복수 개인 경우 exception
            if (newId < 1 || newId > 999)
                throw new SQLDataException("can't retrieve max UserId for OrgId=" + su.getOrgId());
            su.setUserId(newId);
            session.save(su);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.warn("ServiceUser create FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceUser read(int userId, int orgId) {
        Session session = sessionFactory.openSession();
        try {
            // NaturalId 로 조회. using() 인자값은 Class 필드 이름과 일치해야 한다.
            return session.byNaturalId(ServiceUser.class)
                    .using("orgId", orgId)
                    .using("userId", userId)
                    .load();
        } catch (Exception e) {
            log.warn("ServiceUser readById FAIL [{}]", e.getMessage());
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceUser read(String userName) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(ServiceUser.class, userName);
        } catch (Exception e) {
            log.warn("ServiceUser readByName FAIL [{}]", e.getMessage());
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
            log.warn("ServiceUser update FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean delete(String userName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            boolean result = false;
            tx = session.beginTransaction();
            ServiceUser su = session.get(ServiceUser.class, userName);
            if (su != null) {
                session.delete(su);
                result = true;
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            log.warn("ServiceUser delete FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
