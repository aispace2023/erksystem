package com.aispace.erksystem.rmq.module.handler.base.exception;

/**
 * @author kangmoo Heo
 */
public class ValidationHandleException extends RmqHandleException {
    public ValidationHandleException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationHandleException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
