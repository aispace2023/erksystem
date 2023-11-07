package com.aispace.erksystem.service;

import com.aispace.erksystem.common.PasswdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


/**
 * MariaDB 용 DBCP Manager
 *
 * Created by Ai_Space
 *
 */

@Slf4j
public class DBManager {
    private static final DBManager INSTANCE = new DBManager();
    private SessionFactory sessionFactory;
    private static final String CFG_XML = "config/hibernate.cfg.xml";

    private DBManager() {
        // do nothing
    }

    public static DBManager getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void stop() {
        if(sessionFactory != null) sessionFactory.close();
    }

    public boolean start() {
        try {
            Configuration configuration= new Configuration().configure(CFG_XML);
            Properties props = configuration.getProperties();
            String pwd = PasswdUtil.decrypt(props.getProperty("hibernate.hikari.password"));
            props.setProperty("hibernate.hikari.password", pwd);
            sessionFactory = configuration.buildSessionFactory();
//            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//            registryBuilder.applySettings(props);
//            StandardServiceRegistry standardServiceRegistry = registryBuilder.build();
//            sessionFactory = config.buildSessionFactory(standardServiceRegistry);
            return true;
        } catch (Exception e) {
            log.error("Initial SessionFactory creation failed", e);
            return false;
        }
    }
}
