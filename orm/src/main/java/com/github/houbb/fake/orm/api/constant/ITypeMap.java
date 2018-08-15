/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.api.constant;

import java.util.Map;

/**
 * <p> 数据库字段类型映射 </p>
 *
 * <pre> Created: 2018/4/24 下午11:03  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface ITypeMap {

    /**
     * 获取类型集合
     * @return map
     */
    Map<String, String> getTypeMap();

}
