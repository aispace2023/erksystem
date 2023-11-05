package com.aispace.erksystem.service.database;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.service.AppInstance;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * MariaDB 용 DBCP Manager
 *
 * Created by Ai_Space
 *
 */
@Getter
@Setter
@Slf4j
public class DBManager {
    private static final UserConfig config = AppInstance.getInstance().getUserConfig();
    private static final String DBCP_CONF = "/config/hikari.properties";
    private static final DBManager dbManager = new DBManager();
    private HikariDataSource dataSource = null;

    private DBManager() {
    }

    public static DBManager getInstance() {
        return dbManager;
    }

    // hikaricp 설정
    @Synchronized
    public void start() {
        if (dataSource != null) {
            throw new IllegalStateException("DB Connection Pool has already been created.");
        }

        HikariConfig hikariConfig = new HikariConfig(DBCP_CONF);
        StringBuilder jdbcUrl = new StringBuilder("jdbc:mysql://")
                .append(config.getDbHost())
                .append(":").append(config.getDbPort())
                .append("/").append(config.getDbName());
        if (StringUtils.isNotEmpty(config.getJdbcParam()))
            jdbcUrl.append("?").append(config.getJdbcParam());
        hikariConfig.setJdbcUrl(jdbcUrl.toString());
        hikariConfig.setUsername(config.getDbUser());
        hikariConfig.setPassword(config.getDbPassword());
        dataSource = new HikariDataSource(hikariConfig);
    }

    // connection
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // stop
    @Synchronized
    public void stop() {
        if (dataSource != null) {
            log.warn("DB Connection Pool is shutting down.");
            dataSource.close();
            dataSource = null;
        }
    }

}
