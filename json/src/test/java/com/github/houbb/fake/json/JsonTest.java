/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json;


import com.github.houbb.fake.json.api.impl.JsonApiImpl;
import com.github.houbb.fake.json.prepare.UserPrepare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 17:01  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonTest {

    @Test
    public void objectToJsonTest() {
        System.out.println(new JsonApiImpl().toJson(UserPrepare.getUser()));
    }

    @Test
    public void arrayToJson() {
        System.out.println(new JsonApiImpl().toJson(Arrays.asList(UserPrepare.getUser(), UserPrepare.getUser())));
    }

    @Test
    public void jsonToObjectTest() {
        final String json = "{\"lucky\":\"c\",\"character\":\"C\",\"name\":\"hello\",\"id\":1,\"amount\":0,\"time\":1527243047642}";
    }

}
