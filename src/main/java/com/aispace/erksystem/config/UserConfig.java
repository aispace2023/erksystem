package com.aispace.erksystem.config;

import com.aispace.erksystem.common.PasswdUtil;
import com.aispace.erksystem.common.ValidationUtil;
import com.aispace.erksystem.config.base.ConfigValue;
import com.aispace.erksystem.config.base.yaml.YamlConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by Ai_Space
 */
@Getter
@Setter
@ToString
public class UserConfig extends YamlConfig {

    @ConfigValue("rmq.host")
    String rmqHost;
    @ConfigValue("rmq.user")
    String rmqUser;
    @ConfigValue("rmq.password")
    String rmqPassword;
    @ConfigValue("rmq.incoming-queues")
    List<String> incomingQueues;

    @Min(0)
    @ConfigValue("performance.rmq-consumer-count")
    Integer rmqConsumerCount;

    @ConfigValue("dbms.host")
    String dbHost;
    @ConfigValue("dbms.user")
    String dbUser;
    @ConfigValue("dbms.password")
    String dbPassword;
    @ConfigValue("dbms.database")
    String dbName;
    @ConfigValue("dbms.jdbc-params")
    String jdbcParam;
    @ConfigValue("dbms.port")
    Integer dbPort;

    @ConfigValue("prometheus.activate")
    boolean prometheusActivate = false;
    @ConfigValue("prometheus.port")
    Integer prometheusPort;
    @ConfigValue("prometheus.metrics_path")
    String prometheusMetricsPath;

    @Override
    public void afterFieldSetting() {
        rmqPassword = PasswdUtil.decrypt(rmqPassword);
        dbPassword = PasswdUtil.decrypt(dbPassword);
        if (prometheusMetricsPath != null && !prometheusMetricsPath.startsWith("/")) {
            prometheusMetricsPath = "/" + prometheusMetricsPath;
        }
        ValidationUtil.validCheck(this);
    }
}
