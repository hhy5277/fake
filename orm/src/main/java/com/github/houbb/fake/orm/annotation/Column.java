/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 列 </p>
 *
 * <pre> Created: 2018/4/24 下午10:38  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    /**
     * 列名称
     * 1. 默认列名称转下划线对应表字段名称
     * @return 表的名称
     */
    String value() default "";

}
