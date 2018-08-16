/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.dbcp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/16 下午2:16  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class PoolImpl implements IPool {

    /**
     * 数据库驱动
     */
    private final String jdbcDriver;

    /**
     * 数据库连接
     */
    private final String jdbcUrl;

    /**
     * 数据库用户名
     */
    private final String username;

    /**
     * 数据库密码
     */
    private final String passowrd;

    /**
     * 连接池大小
     */
    private final int size;

    /**
     * 数据库连接池列表
     */
    private List<PoolConnection> poolConnections = new ArrayList<>();

    public PoolImpl(String jdbcDriver, String jdbcUrl, String username, String passowrd, int size) {
        this.jdbcDriver = jdbcDriver;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.passowrd = passowrd;
        this.size = size;

        init();
    }



    private void init() {
        try {
            //1. 注册数据库连接信息
            Driver sqlDriver = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(sqlDriver);

            //2. 初始化连接池
            initConnectionPool();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化链接
     * @throws SQLException sql 异常
     */
    private void initConnectionPool() throws SQLException {
        for(int i = 0; i < size; i++) {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, passowrd);
            PoolConnection poolConnection = new PoolConnection(false, connection);
            poolConnections.add(poolConnection);
        }
    }

    @Override
    public PoolConnection getPoolConnection() {
        if(poolConnections.size() <= 0) {
            return null;
        }

        PoolConnection poolConnection = getRealConnection();
        while (poolConnection == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            poolConnection = getRealConnection();
        }

        return poolConnection;
    }

    /**
     * 获取数据库链接对象
     * @return 数据库链接对象
     */
    private synchronized PoolConnection getRealConnection() {
        for(PoolConnection poolConnection : poolConnections) {
            // 寻找不处于繁忙状态的连接
            if(!poolConnection.isBusy()) {
                Connection connection = poolConnection.getConnection();

                // 测试当前连接是否有效
                try {
                    if(!connection.isValid(5000)) {
                        Connection validConnection = DriverManager.getConnection(jdbcUrl, username, passowrd);
                        poolConnection.setConnection(validConnection);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // 设置为繁忙
                poolConnection.setBusy(true);
                return poolConnection;
            }
        }

        return null;
    }

}
