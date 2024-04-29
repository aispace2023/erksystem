package com.aispace.erksystem.config;

import com.aispace.erksystem.common.PasswdUtil;
import com.aispace.erksystem.common.ValidationUtil;
import com.aispace.erksystem.config.base.ConfigValue;
import com.aispace.erksystem.config.base.yaml.YamlConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

/**
 * Created by Ai_Space
 */
@Getter
@Setter
@ToString
public class UserConfig extends YamlConfig {
    public static final String RMQ_KEY = "RMQ";

    @ConfigValue("rmq.host")
    String rmqHost;
    @ConfigValue("rmq.user")
    String rmqUser;
    @ConfigValue("rmq.password")
    String rmqPassword;
    @ConfigValue("rmq.port")
    int rmqPort;
    @ConfigValue("rmq.buffer-count")
    int bufferCount = 1024;
    @ConfigValue("rmq.incoming-queue.api")
    String rmqIncomingQueueApi;
    @ConfigValue("rmq.incoming-queue.subsystem")
    String rmqIncomingQueueSubsystem;
    @ConfigValue("rmq.outgoing-queue.api")
    String rmqOutgoingQueueApi;
    @ConfigValue("rmq.outgoing-queue.subsystem")
    String rmqOutgoingQueueSubsystem;

    @ConfigValue("rmq.agent-option.durable")
    boolean agentOptionDurable = true;
    @ConfigValue("rmq.agent-option.exclusive")
    boolean agentOptionExclusive = false;
    @ConfigValue("rmq.agent-option.autoDelete")
    boolean agentOptionAutoDelete = false;

    @Min(0)
    @ConfigValue("timer.connection-timeout")
    int connectionTimeout;

    @Min(0)
    @ConfigValue("performance.rmq-consumer-count")
    Integer rmqConsumerCount;

    @ConfigValue("prometheus.activate")
    boolean prometheusActivate = false;
    @ConfigValue("prometheus.port")
    Integer prometheusPort;
    @ConfigValue("prometheus.metrics_path")
    String prometheusMetricsPath;

    @Override
    public void afterFieldSetting() {
        rmqPassword = PasswdUtil.decrypt(rmqPassword);
        if (prometheusMetricsPath != null && !prometheusMetricsPath.startsWith("/")) {
            prometheusMetricsPath = "/" + prometheusMetricsPath;
        }
        ValidationUtil.validCheck(this);
    }
}
