/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.orm.mysql;


import com.github.houbb.fake.orm.api.constant.ITypeMap;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 字段映射实现 </p>
 *
 * <pre> Created: 2018/4/24 下午11:09  </pre>
 * <pre> Project: orm  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class TypeMapImpl implements ITypeMap {

    private static final Map<String, String> typeMap = new HashMap<>();

    static {
        typeMap.put("java.lang.String", "VARCHAR");
        typeMap.put("char", "CHAR");
        typeMap.put("java.lang.Character", "CHAR");
        typeMap.put("boolean", "BIT");
        typeMap.put("java.lang.Boolean", "BIT");
        typeMap.put("byte", "TINYINT");
        typeMap.put("short", "SMALLINT");
        typeMap.put("java.lang.Byte", "SMALLINT");
        typeMap.put("int", "INTEGER");
        typeMap.put("java.lang.Integer", "INTEGER");
        typeMap.put("long", "BIGINT");
        typeMap.put("java.lang.Long", "BIGINT");
        typeMap.put("float", "FLOAT");
        typeMap.put("java.lang.Float", "FLOAT");
        typeMap.put("double", "DOUBLE");
        typeMap.put("java.lang.Double", "DOUBLE");
        typeMap.put("java.util.Date", "DATETIME");
    }

    @Override
    public Map<String, String> getTypeMap() {
        return typeMap;
    }

}
