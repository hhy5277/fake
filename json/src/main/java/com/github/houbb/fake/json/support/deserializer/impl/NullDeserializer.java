/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.support.deserializer.impl;

import com.github.houbb.fake.json.support.deserializer.Deserializer;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/5/25 下午6:15  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class NullDeserializer implements Deserializer {

    @Override
    public Object deserialize(String json) {
        return null;
    }

}
