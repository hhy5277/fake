/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.dto;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 2018/4/17
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class HandlerMethodDto {

    /**
     * 调用的方法信息
     */
    private Method method;

    /**
     * 对应的控制类信息
     */
    private Object controller;

    /**
     * 参数列表
     */
    private Set<ParamDto> params;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Set<ParamDto> getParams() {
        return params;
    }

    public void setParams(Set<ParamDto> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "HandlerMethodDto{" +
                "method=" + method +
                ", controller=" + controller +
                ", params=" + params +
                '}';
    }
}
