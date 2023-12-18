package com.aispace.erksystem.rmq.handler.base.exception;

/**
 * Created by Ai_Space
 */
public class ValidationHandleException extends RmqHandleException {
    public ValidationHandleException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationHandleException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
