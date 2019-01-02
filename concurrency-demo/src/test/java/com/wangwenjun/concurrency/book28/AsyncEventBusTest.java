package com.wangwenjun.concurrency.book28;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:11
 */
public class AsyncEventBusTest {

    public static void main(String[] args) {

        Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));

        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());

        bus.post("Hello");

        System.out.println("================");

        bus.post("Hello2", "test");
    }

}
