/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.rpc.consumer;

import com.github.houbb.fake.rpc.common.service.Calculator;
import com.github.houbb.fake.rpc.consumer.proxy.CalculatorProxy;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/24 下午4:57  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class RpcClient {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorProxy();
        int result = calculator.add(1, 2);
        System.out.println(result);
    }

}
