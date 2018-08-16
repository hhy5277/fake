/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.dbcp;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/8/16 下午2:13  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface IPool {

    /**
     * 获取新的数据库链接
     * @return 数据库链接
     */
    PoolConnection getPoolConnection();


}
