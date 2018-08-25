/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.rpc.consumer.proxy;

import com.github.houbb.fake.rpc.common.constant.RpcConstant;
import com.github.houbb.fake.rpc.common.model.RpcCalculateRequest;
import com.github.houbb.fake.rpc.common.service.Calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/24 下午4:58  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class CalculatorProxy implements Calculator {

    @Override
    public int add(int one, int two) {
        try {
            Socket socket = new Socket(RpcConstant.ADDRESS, RpcConstant.PORT);

            // 将请求序列化
            RpcCalculateRequest calculateRpcRequest = new RpcCalculateRequest(one, two);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 将请求发给服务提供方
            objectOutputStream.writeObject(calculateRpcRequest);

            // 将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            if (response instanceof Integer) {
                return (Integer) response;
            } else {
                throw new RuntimeException();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
