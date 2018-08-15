/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.api.dto;

import java.util.List;

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
public class Entity {

    /**
     * 实体名称
     */
    private String name;

    /**
     * id 列字段名称
     */
    private String idColumnName;

    /**
     * 列信息
     */
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
