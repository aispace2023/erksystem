package com.aispace.erksystem.service;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.jvm.*;
import io.micrometer.core.instrument.binder.logging.LogbackMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.Getter;

import java.io.File;

/**
 * Created by Ai_Space
 */
@Getter
public class PrometheusManager {
    PrometheusMeterRegistry registry;
    private PrometheusManager() {
        registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        // new DatabaseTableMetrics().bindTo(registry);
        // new ExecutorServiceMetrics().bindTo(registry);
        // new HibernateMetrics().bindTo(registry);
        // new HibernateQueryMetrics().bindTo(registry);
        new ClassLoaderMetrics().bindTo(registry);
        new DiskSpaceMetrics(new File("/")).bindTo(registry);
        new FileDescriptorMetrics().bindTo(registry);
        new JvmCompilationMetrics().bindTo(registry);
        new JvmGcMetrics().bindTo(registry);
        new JvmHeapPressureMetrics().bindTo(registry);
        new JvmInfoMetrics().bindTo(registry);
        new JvmMemoryMetrics().bindTo(registry);
        new JvmThreadMetrics().bindTo(registry);
        new LogbackMetrics().bindTo(registry);
        new ProcessorMetrics().bindTo(registry);
        new UptimeMetrics().bindTo(registry);

        Metrics.addRegistry(registry);
    }

    private static class SingletonInstance {
        private static final PrometheusManager INSTANCE = new PrometheusManager();
    }

    public static PrometheusManager getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
