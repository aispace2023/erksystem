package com.aispace.erksystem.service.prometheus;

import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import lombok.Getter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Ai_Space
 */
@Getter
public class PrometheusManager {
    private static final PrometheusManager INSTANCE = new PrometheusManager();
    private final AtomicBoolean initialized = new AtomicBoolean();
    private HTTPServer server;

    private PrometheusManager() {
    }

    public static PrometheusManager getInstance() {
        return INSTANCE;
    }

    public void initialize(InetSocketAddress addr, CollectorRegistry registry, boolean daemon) throws IOException {
        if (initialized.compareAndSet(false, true)) {
            server = new HTTPServer(HttpServer.create(addr, 3), registry, daemon);
            register(CollectorRegistry.defaultRegistry);
        }
    }

    public void initialize(InetSocketAddress addr, CollectorRegistry registry) throws IOException {
        this.initialize(addr, registry, false);
    }

    public void initialize(int port, boolean daemon) throws IOException {
        this.initialize(new InetSocketAddress(port), CollectorRegistry.defaultRegistry, daemon);
    }

    public void initialize(int port) throws IOException {
        this.initialize(port, false);
    }

    public void initialize(String host, int port, boolean daemon) throws IOException {
        this.initialize(new InetSocketAddress(host, port), CollectorRegistry.defaultRegistry, daemon);
    }

    public void initialize(String host, int port) throws IOException {
        this.initialize(new InetSocketAddress(host, port), CollectorRegistry.defaultRegistry, false);
    }

    public void register(CollectorRegistry registry) {
        DefaultExports.initialize();
        new OsInfoExports().register(registry);
    }

    public void close() {
        if (server != null) {
            server.close();
        }
    }
}
