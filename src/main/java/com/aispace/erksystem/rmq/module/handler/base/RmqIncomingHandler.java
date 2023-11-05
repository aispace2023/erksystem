package com.aispace.erksystem.rmq.module.handler.base;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.service.AppInstance;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.common.SafeExecutor.tryRun;


/**
 * @author kangmoo Heo
 */
@Slf4j
public abstract class RmqIncomingHandler<T extends GeneratedMessageV3> implements RmqIncomingMsgInterface {
    protected static final AppInstance appInstance = AppInstance.getInstance();
    protected static final UserConfig userConfig = appInstance.getUserConfig();
    protected static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    protected Message incomingMsg;
    protected T msg;

    @SuppressWarnings("unchecked")
    public void proc(Message incomingMsg) {
        try {
            this.incomingMsg = incomingMsg;
            this.msg = (T) incomingMsg.getAllFields().values().stream().findAny().orElseThrow();
            handle();
        } catch (Exception e) {
            log.warn("Err Occurs while handle message", e);
            tryRun(this::onFail);
        }
    }

    protected abstract void handle();

    protected abstract void onFail();
}
