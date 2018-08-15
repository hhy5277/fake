/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.api.metadata;


import com.github.houbb.fake.orm.api.dto.Entity;

import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/4/24 下午11:26  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface IEntityInfo {

    /**
     * 查询所有的实体列表信息
     * @param tablesName 表名称
     * @return 对应的集合信息
     */
    List<Entity> queryEntityList(String... tablesName);

}
