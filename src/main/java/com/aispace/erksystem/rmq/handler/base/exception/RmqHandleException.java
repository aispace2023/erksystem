package com.aispace.erksystem.rmq.handler.base.exception;

import lombok.Getter;

/**
 * Created by Ai_Space
 */
@Getter
public class RmqHandleException extends RuntimeException {
    private final int errCode;

    public RmqHandleException(int errorCode, String message) {
        super(message);
        this.errCode = errorCode;
    }

    public RmqHandleException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errorCode;
    }
}
