/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.exception;

/**
 * 2018/4/12
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class MvcRuntimeException extends RuntimeException {

    public MvcRuntimeException() {
    }

    public MvcRuntimeException(String message) {
        super(message);
    }

    public MvcRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MvcRuntimeException(Throwable cause) {
        super(cause);
    }

    public MvcRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
