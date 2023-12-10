package com.aispace.erksystem.rmq.module.handler.base;

import com.google.protobuf.Message;

/**
 * Created by Ai_Space
 */
public interface RmqIncomingMsgInterface {
    void proc(Message incomingMsg);
}
