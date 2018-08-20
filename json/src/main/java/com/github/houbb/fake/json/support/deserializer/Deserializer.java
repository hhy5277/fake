/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.support.deserializer;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:19  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public interface Deserializer<T> {

    T deserialize(final String json);

}
