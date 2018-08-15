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
 * <p> 实体表 </p>
 *
 * <pre> Created: 2018/4/24 下午10:38  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {

    /**
     * 表名称
     * 1. 默认使用实体的名称转下划线对应表名称
     * @return 表的名称
     */
    String value() default "";

}
