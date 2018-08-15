/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.utils;

/**
 * 2018/4/12
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class StringUtils {

    public static final String EMPTY = "";

    /**
     * 首字母小写
     * @param name
     * @return
     */
    public static String firstCharLower(String name) {
        char[] chars = name.toCharArray();
        char firstChar = chars[0];
        if(Character.isUpperCase(firstChar)) {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

    public static boolean isEmpty(String string) {
        if(null == string
                || string.trim().equals(EMPTY)) {
            return true;
        }
        return false;

    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

}
