/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.rpc.common.server;

import com.github.houbb.fake.rpc.common.constant.RpcConstant;
import com.github.houbb.fake.rpc.common.model.RpcCalculateRequest;
import com.github.houbb.fake.rpc.common.service.Calculator;
import com.github.houbb.fake.rpc.common.service.CalculatorImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/24 下午4:52  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class RpcServer {

    public static void main(String[] args) throws IOException {
        Calculator calculator = new CalculatorImpl();

        try (ServerSocket listener = new ServerSocket(RpcConstant.PORT)) {
            System.out.println("Server 端启动：" + RpcConstant.ADDRESS + ":" + RpcConstant.PORT);
            while (true) {
                try (Socket socket = listener.accept()) {
                    // 将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();
                    System.out.println("Request is: " + object);

                    // 调用服务
                    int result = 0;
                    if (object instanceof RpcCalculateRequest) {
                        RpcCalculateRequest calculateRpcRequest = (RpcCalculateRequest) object;
                        result = calculator.add(calculateRpcRequest.getOne(), calculateRpcRequest.getTwo());
                    }

                    // 返回结果
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
