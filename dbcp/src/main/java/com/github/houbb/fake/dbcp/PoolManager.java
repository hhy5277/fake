/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.dbcp;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/16 下午2:43  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class PoolManager {

    /**
     * 连接池持有类
     */
    private static class PoolHolder {
        private static String url = "";
        private static String driver = "";
        private static String username = "";
        private static String password = "";
        private static int size = 10;

        private static IPool poolImpl = new PoolImpl(driver, url, username, password, size);
    }

    /**
     * 内部类单利模式产生使用对象
     * @return 单例
     */
    public static IPool getInstance() {
        return PoolHolder.poolImpl;
    }

}
