/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.utils;

import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:37  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public final class ObjectUtils {

    private ObjectUtils(){}

    public static boolean isArrayOrList(final Object object) {
        return isArray(object)
                && isList(object);
    }

    public static boolean isArray(final Object object) {
        return object.getClass().isArray();
    }

    public static boolean isMap(final Object object) {
        return false;
    }

    public static boolean isList(final Object object) {
        return List.class.isAssignableFrom(object.getClass());
    }

}
