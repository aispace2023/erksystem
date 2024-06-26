package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLDataException;

@Slf4j
public class ServiceProviderDAO {
    private static final DBManager dbManager = DBManager.getInstance();

    private ServiceProviderDAO() {}

    public static boolean create(ServiceProvider sp) {
        Session session = dbManager.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Integer> query = session.createNativeQuery("SELECT COALESCE(MAX(OrgId), 0) AS id_max FROM SERVICE_PROVIDER_TBL");
            int newId = query.getSingleResult() + 1;   // query result 가 없거나 복수 개인 경우 exception
            if (newId < 1 || newId > 999) throw new SQLDataException("can't retrieve max OrgID");
            sp.setOrgId(newId);
            session.save(sp);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.warn("ServiceProvider create FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider read(int orgId) {
        Session session = dbManager.getSessionFactory().openSession();
        try {
            return session.get(ServiceProvider.class, orgId);
        } catch (Exception e) {
            log.warn("ServiceProvider readById FAIL [{}]", e.getMessage());
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider read(String orgName) {
        Session session = dbManager.getSessionFactory().openSession();
        try {
            return session.bySimpleNaturalId(ServiceProvider.class).load(orgName);
        } catch (Exception e) {
            log.warn("ServiceProvider readByName FAIL [{}]", e.getMessage());
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean update(ServiceProvider sp) {
        Session session = dbManager.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(sp);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.warn("ServiceProvider update FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean delete(int orgId) {
        Session session = dbManager.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            boolean result = false;
            tx = session.beginTransaction();
            ServiceProvider sp = session.get(ServiceProvider.class, orgId);
            if (sp != null) {
                session.delete(sp);
                result = true;
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            log.warn("ServiceProvider delete FAIL [{}]", e.getMessage());
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
