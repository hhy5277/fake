/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.rpc.common.service;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/24 下午4:49  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int one, int two) {
        return one + two;
    }

}
