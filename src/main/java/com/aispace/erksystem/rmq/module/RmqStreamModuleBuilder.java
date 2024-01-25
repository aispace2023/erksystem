package com.aispace.erksystem.rmq.module;

public final class RmqStreamModuleBuilder {
    RmqModuleBuilder rmqModuleBuilder;
    int qos = 100;
    Object streamOffset = "next";

    RmqStreamModuleBuilder(String host, String userName, String password) {
        this.rmqModuleBuilder = new RmqModuleBuilder(host, userName, password);
    }

    public RmqStreamModuleBuilder setHost(String host) {
        rmqModuleBuilder.setHost(host);
        return this;
    }

    public RmqStreamModuleBuilder setUserName(String userName) {
        rmqModuleBuilder.setUserName(userName);
        return this;
    }

    public RmqStreamModuleBuilder setPassword(String password) {
        rmqModuleBuilder.setPassword(password);
        return this;
    }

    public RmqStreamModuleBuilder setPort(int port) {
        rmqModuleBuilder.setPort(port);
        return this;
    }

    public RmqStreamModuleBuilder setBufferCount(int bufferCount) {
        rmqModuleBuilder.setBufferCount(bufferCount);
        return this;
    }

    public RmqStreamModuleBuilder setRecoveryInterval(int recoveryInterval) {
        rmqModuleBuilder.setRecoveryInterval(recoveryInterval);
        return this;
    }

    public RmqStreamModuleBuilder setRequestedHeartbeat(int requestedHeartbeat) {
        rmqModuleBuilder.setRequestedHeartbeat(requestedHeartbeat);
        return this;
    }

    public RmqStreamModuleBuilder setConnectionTimeout(int connectionTimeout) {
        rmqModuleBuilder.setConnectionTimeout(connectionTimeout);
        return this;
    }

    public RmqStreamModuleBuilder setOnConnected(Runnable onConnected) {
        rmqModuleBuilder.setOnConnected(onConnected);
        return this;
    }

    public RmqStreamModuleBuilder setOnDisconnected(Runnable onDisconnected) {
        rmqModuleBuilder.setOnDisconnected(onDisconnected);
        return this;
    }

    public RmqStreamModuleBuilder setQos(int qos) {
        this.qos = qos;
        return this;
    }

    public RmqStreamModuleBuilder setStreamOffset(Object streamOffset) {
        this.streamOffset = streamOffset;
        return this;
    }

    public RmqStreamModule build() {
        return new RmqStreamModule(rmqModuleBuilder.build(), qos, streamOffset);
    }
}
