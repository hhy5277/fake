/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.threadpool.base;

import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p> </p>
 *
 * https://blog.csdn.net/yingfengjia520/article/details/78234739
 * <pre> Created: 2018/8/15 上午10:02  </pre>
 * <pre> Project: fake  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class ThreadPoolTest {

    private static final Log log = LogFactory.getLog(ThreadPoolTest.class);


    public static void main(String[] args) {
        ThreadPoolImpl threadPool = new ThreadPoolImpl(10);

        List<Runnable> runnableList = new ArrayList<>();
        final int count = 20;
        for(int i = 0; i < count; i++) {
            int finalI = i;
            runnableList.add(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("Task runnable: {}", finalI);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.execute(runnableList);

        // 等到全部任务完成 关闭线程池
        while(count > threadPool.getExecutedCount()) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.stopAll();
    }

}
