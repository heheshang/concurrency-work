package com.wangwenjun.concurrency.book28;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:05
 */
public class EventBusTest {

    public static void main(String[] args) {
        Bus bus = new EventBus("TestBus");

        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());

        bus.post("Hello");

        System.out.println("=========================");
        bus.post("Hello2","test");
    }

}
