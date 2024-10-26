package com.aispace.erksystem.rmq.handler.base;

import com.aispace.erksystem.common.utils.PromiseManager;
import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.connection.ConnectionManager;
import com.aispace.erksystem.rmq.ErkMsgUtil;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.rmq.handler.base.exception.RmqHandleException;
import com.aispace.erksystem.rmq.handler.base.exception.ValidationHandleException;
import com.aispace.erksystem.service.AppInstance;
import com.erksystem.protobuf.api.QueueInfo_s;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

import static com.aispace.erksystem.rmq.ErkMsgUtil.setQueueInfo;
import static com.aispace.erksystem.rmq.handler.base.ErkMsgWrapper.wrap2ErkApiMsg;
import static com.erksystem.protobuf.api.ReturnCode_e.ReturnCode_unknown;


/**
 * Created by Ai_Space
 */
@Slf4j
public abstract class RmqIncomingHandler<T extends GeneratedMessage> implements RmqIncomingMsgInterface {
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
            onFail(ReturnCode_unknown.getNumber(), "Unexpected Exception");
        }
    }

    protected abstract void handle();

    protected abstract void onFail(int reasonCode, String reason);

    /**
     * QueueInfo 정보를 변경하여 응답을 전송한다
     *
     * @param msg 응답 메시지
     */
    public void reply(Message msg) {
        QueueInfo_s oldQueueInfo = ErkMsgUtil.getQueueInfo(incomingMsg).orElseThrow();
        String rmqKey = getProperRmqKey();
        QueueInfo_s newQueueInfo = QueueInfo_s.newBuilder()
                .setFromQueueName(rmqKey)
                .setToQueueName(oldQueueInfo.getFromQueueName()).build();

        RmqOutgoingHandler.send(wrap2ErkApiMsg(setQueueInfo(msg, newQueueInfo)), newQueueInfo.getToQueueName());
    }

    private String getProperRmqKey() {
        String packageName = getClass().getPackageName();
        if (packageName.contains(".api")) {
            return userConfig.getRmqIncomingQueueApi();
        } else if (packageName.endsWith(".subsystem")) {
            return userConfig.getRmqIncomingQueueSubsystem();
        } else {
            throw new IllegalStateException("Unexpected Package Name: " + packageName);
        }
    }
}
