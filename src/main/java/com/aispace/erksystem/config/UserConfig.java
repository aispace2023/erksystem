package com.aispace.erksystem.config;

import com.aispace.erksystem.common.PasswdUtil;
import com.aispace.erksystem.common.ValidationUtil;
import com.aispace.erksystem.config.base.ConfigValue;
import com.aispace.erksystem.config.base.yaml.YamlConfig;
import com.erksystem.protobuf.api.EngineType_e;
import com.erksystem.protobuf.api.ServiceType_e;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

import static com.erksystem.protobuf.api.EngineType_e.*;
import static com.erksystem.protobuf.api.ServiceType_e.*;

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
    @ConfigValue("rmq.outgoing-queue.per-queue")
    String rmqOutgoingPerQueue;
    @ConfigValue("rmq.outgoing-queue.ser-queue")
    String rmqOutgoingSerQueue;
    @ConfigValue("rmq.outgoing-queue.fer-queue")
    String rmqOutgoingFerQueue;
    @ConfigValue("rmq.outgoing-queue.ekm-queue")
    String rmqOutgoingEkmQueue;

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

    @ConfigValue("timeout.erk-hb-timeout")
    long erkHbTimeout = 5000;

    Map<EngineType_e, String> engineQueueMap;

    final Map<ServiceType_e, List<EngineType_e>> serviceTypeMap = Map.of(
            ServiceType_physiology, List.of(EngineType_physiology),
            ServiceType_speech, List.of(EngineType_speech),
            ServiceType_video, List.of(EngineType_face),
            ServiceType_physiology_speech, List.of(EngineType_physiology, EngineType_speech),
            ServiceType_physiology_video, List.of(EngineType_physiology, EngineType_face),
            ServiceType_speech_video, List.of(EngineType_speech, EngineType_face),
            ServiceType_physiology_speech_video, List.of(EngineType_physiology, EngineType_speech, EngineType_face),
            ServiceType_knowledge, List.of(EngineType_knowledge),
            ServiceType_service_all, List.of(EngineType_physiology, EngineType_speech, EngineType_face, EngineType_knowledge)
    );

    @Override
    public void afterFieldSetting() {
        rmqPassword = PasswdUtil.decrypt(rmqPassword);
        if (prometheusMetricsPath != null && !prometheusMetricsPath.startsWith("/")) {
            prometheusMetricsPath = "/" + prometheusMetricsPath;
        }

        engineQueueMap = Map.of(
                EngineType_e.EngineType_physiology, rmqOutgoingPerQueue,
                EngineType_e.EngineType_speech, rmqOutgoingSerQueue,
                EngineType_e.EngineType_face, rmqOutgoingFerQueue,
                EngineType_e.EngineType_knowledge, rmqOutgoingEkmQueue
        );

        ValidationUtil.validCheck(this);
    }
}
