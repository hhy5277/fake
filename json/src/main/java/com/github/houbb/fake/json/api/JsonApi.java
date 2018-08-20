/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.api;


import com.github.houbb.fake.json.support.config.JsonConfig;

import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/5/3 下午10:15  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface JsonApi {

    /**
     * 转化为Json
     * @param object 对象
     * @return json
     */
    String toJson(Object object);

    /**
     * 转化为Json
     * @param object 对象
     * @param jsonConfig 配置
     * @return json
     */
    String toJson(Object object, JsonConfig jsonConfig);

    /**
     * 转换为对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T toObject(final String json, final Class<T> tClass);

    /**
     * 转换为列表
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> toArray(final String json, final Class<T> tClass);

}
