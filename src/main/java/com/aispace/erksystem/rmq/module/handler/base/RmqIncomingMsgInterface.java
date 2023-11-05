package com.aispace.erksystem.rmq.module.handler.base;

import com.google.protobuf.Message;

/**
 * @author kangmoo Heo
 */
public interface RmqIncomingMsgInterface {
    void proc(Message incomingMsg);
}
