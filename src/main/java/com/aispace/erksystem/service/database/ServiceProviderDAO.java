package com.aispace.erksystem.service.database;

import com.aispace.erksystem.service.DBManager;
import com.aispace.erksystem.service.database.table.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLDataException;

@Slf4j
public class ServiceProviderDAO {
    private static final SessionFactory sessionFactory = DBManager.getInstance().getSessionFactory();

    private ServiceProviderDAO() {}

    public static boolean create(ServiceProvider sp) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query<Integer> query = session.createNativeQuery("SELECT COALESCE(MAX(org_id), 0) AS id_max FROM service_provider_tbl");
            int newId = query.getSingleResult() + 1;   // query result 가 없거나 복수 개인 경우 exception
            if (newId < 1 || newId > 999) throw new SQLDataException("can't retrieve max org_id");
            sp.setOrgId(newId);
            session.save(sp);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.warn("insert error - {}", e.getMessage());
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
            log.warn("read error - {}", e.getMessage());
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static ServiceProvider read(String orgName) {
        Session session = sessionFactory.openSession();
        try {
            return session.bySimpleNaturalId(ServiceProvider.class).load(orgName);
        } catch (Exception e) {
            log.warn("read error - {}", e.getMessage());
            return null;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean update(ServiceProvider sp) {
        Session session = sessionFactory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(sp);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.warn("update error - {}", e.getMessage());
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    public static boolean delete(int orgId) {
        Session session = sessionFactory.openSession();
        try {
            boolean result = false;
            Transaction tx = session.beginTransaction();
            ServiceProvider sp = session.get(ServiceProvider.class, orgId);
            if (sp != null) {
                session.delete(sp);
                result = true;
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            log.warn("delete error - {}", e.getMessage());
            return false;
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }
}
