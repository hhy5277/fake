/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.hibernate.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列-注解
 * Created by houbinbin on 16/6/5.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Column {

    /**
     * 列名
     * @return 列名
     */
    String value() default "";

    /**
     * 是否可以为空
     * @return {@code true} 可以
     */
    boolean nullable() default true;

    /**
     * 字段的长度
     * @return 字段的长度
     */
    int length() default 255;

}
