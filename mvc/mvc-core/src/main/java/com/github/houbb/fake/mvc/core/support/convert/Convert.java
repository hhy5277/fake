/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.support.convert;

/**
 * 2018/4/18
 * 用于参数的类型转换
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public interface Convert {

    /**
     * 类型装换
     * @param original 原始信息
     * @param tClass 转换的对象结果
     * @param <T> 泛型
     * @return 转换结果
     */
    <T> Class<T>  convert(Object original, Class<T> tClass);

}
