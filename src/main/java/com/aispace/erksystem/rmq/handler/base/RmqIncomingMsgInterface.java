package com.aispace.erksystem.rmq.handler.base;

import com.google.protobuf.Message;

/**
 * Created by Ai_Space
 */
public interface RmqIncomingMsgInterface {
    void proc(Message incomingMsg);
}
