package com.aispace.erksystem.rmq.module.handler.base.exception;

import lombok.Getter;

/**
 * @author kangmoo Heo
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
