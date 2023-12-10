package com.aispace.erksystem.rmq.module.handler.base;

import com.aispace.erksystem.common.utils.PromiseManager;
import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.module.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.module.handler.base.exception.ValidationHandleException;
import com.aispace.erksystem.service.AppInstance;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Ai_Space
 */
@Slf4j
public abstract class RmqIncomingHandler<T extends GeneratedMessageV3> implements RmqIncomingMsgInterface {
    protected static final AppInstance appInstance = AppInstance.getInstance();
    protected static final RmqManager rmqManager = RmqManager.getInstance();
    protected static final PromiseManager promiseManager = PromiseManager.getInstance();
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
        } catch (ValidationHandleException e) {
            log.warn("Validation Fail. {}", e.getMessage(), e);
            onFail(e.getErrCode(), e.getMessage());
        } catch (RmqHandleException e) {
            log.warn("Fail to handle message. {}", e.getMessage(), e);
            onFail(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.warn("Unexpected Exception Occurs", e);
            onFail(0, "Unexpected Exception");
        }
    }

    protected abstract void handle();

    protected abstract void onFail(int reasonCode, String reason);
}
