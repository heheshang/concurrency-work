package com.wangwenjun.concurrency.third.future;


import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;


/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 4:18
 */
public class FutureExample6 {

    static void completedFutureExample() {

        CompletableFuture<String> f = CompletableFuture.completedFuture("message");

        Assert.isTrue(f.isDone());
    }
}
