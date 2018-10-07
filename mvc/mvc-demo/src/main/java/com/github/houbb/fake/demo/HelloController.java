/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.demo;

import com.github.houbb.fake.mvc.annotation.Controller;
import com.github.houbb.fake.mvc.annotation.RequestMapping;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/10/7 上午10:05  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "hello mvc";
    }

}
