/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.rpc.common.model;

import java.io.Serializable;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/24 下午5:05  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class RpcCalculateRequest implements Serializable {

    private static final long serialVersionUID = 6420751004355300996L;

    /**
     * 参数一
     */
    private int one;

    /**
     * 参数二
     */
    private int two;

    public RpcCalculateRequest() {
    }

    public RpcCalculateRequest(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    @Override
    public String toString() {
        return "RpcCalculateRequest{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
