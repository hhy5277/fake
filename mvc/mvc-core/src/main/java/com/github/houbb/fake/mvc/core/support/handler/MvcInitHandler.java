/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.support.handler;

import java.util.List;
import java.util.Map;

/**
 * 2018/4/12
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public interface MvcInitHandler {

    /**
     * 当包扫描结束之后
     * @param classList class 信息列表
     */
    void afterScanPackage(final List<Class> classList);

    /**
     * 在所有的类实例化之后
     * @param instanceMap 实例 map
     */
    void afterClassInstance(Map<String, Object> instanceMap);

}
