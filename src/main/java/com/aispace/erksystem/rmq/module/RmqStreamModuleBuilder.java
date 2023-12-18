package com.aispace.erksystem.rmq.module;

/**
 * @author kangmoo Heo
 */
public final class RmqStreamModuleBuilder {
    RmqModuleBuilder rmqModuleBuilder;
    int qos = 100;
    Object streamOffset = "next";

    RmqStreamModuleBuilder(String host, String userName, String password) {
        this.rmqModuleBuilder = new RmqModuleBuilder(host, userName, password);
    }

    public RmqModuleBuilder setHost(String host) {
        return rmqModuleBuilder.setHost(host);
    }

    public RmqModuleBuilder setUserName(String userName) {
        return rmqModuleBuilder.setUserName(userName);
    }

    public RmqModuleBuilder setPassword(String password) {
        return rmqModuleBuilder.setPassword(password);
    }

    public RmqModuleBuilder setPort(int port) {
        return rmqModuleBuilder.setPort(port);
    }

    public RmqModuleBuilder setBufferCount(int bufferCount) {
        return rmqModuleBuilder.setBufferCount(bufferCount);
    }

    public RmqModuleBuilder setRecoveryInterval(int recoveryInterval) {
        return rmqModuleBuilder.setRecoveryInterval(recoveryInterval);
    }

    public RmqModuleBuilder setRequestedHeartbeat(int requestedHeartbeat) {
        return rmqModuleBuilder.setRequestedHeartbeat(requestedHeartbeat);
    }

    public RmqModuleBuilder setConnectionTimeout(int connectionTimeout) {
        return rmqModuleBuilder.setConnectionTimeout(connectionTimeout);
    }

    public RmqModuleBuilder setOnConnected(Runnable onConnected) {
        return rmqModuleBuilder.setOnConnected(onConnected);
    }

    public RmqModuleBuilder setOnDisconnected(Runnable onDisconnected) {
        return rmqModuleBuilder.setOnDisconnected(onDisconnected);
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
