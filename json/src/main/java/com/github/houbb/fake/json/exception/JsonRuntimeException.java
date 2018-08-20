/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.exception;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:30  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonRuntimeException extends RuntimeException {

    public JsonRuntimeException() {
    }

    public JsonRuntimeException(String message) {
        super(message);
    }

    public JsonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonRuntimeException(Throwable cause) {
        super(cause);
    }

    public JsonRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
