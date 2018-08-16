/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.dbcp;

import java.sql.Connection;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/16 下午2:11  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class PoolConnection {

    /**
     * 是否繁忙
     */
    private volatile boolean isBusy;

    /**
     * 数据库链接信息
     */
    private Connection connection;

    public PoolConnection(boolean isBusy, Connection connection) {
        this.isBusy = isBusy;
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
