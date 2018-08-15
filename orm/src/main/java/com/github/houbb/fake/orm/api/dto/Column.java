/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.api.dto;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/4/24 下午11:18  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class Column {

    /**
     * 列名称
     */
    private String name;

    /**
     * 数据库字段类型
     */
    private String databaseType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
}
