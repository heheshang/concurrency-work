package com.wangwenjun.concurrency.book28;

import com.wangwenjun.concurrency.book28.bus.Bus;
import com.wangwenjun.concurrency.book28.bus.EventBus;
import com.wangwenjun.concurrency.book28.dispatcher.Dispatcher;
import com.wangwenjun.concurrency.book28.listener.SimpleSubscriber1;
import com.wangwenjun.concurrency.book28.listener.SimpleSubscriber2;

import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:05
 */
public class EventBusTest {

    public static void main(String[] args) throws InterruptedException {
        Bus bus = new EventBus("TestBus");

        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());

        bus.post("Hello");

        System.out.println("=========================");
        bus.post("Hello2","test");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        TimeUnit.SECONDS.sleep(5L);

        Bus bus1 = new EventBus("TestBus", Dispatcher.preThreadDispatcher(null));

        bus1.register(new SimpleSubscriber1());
        bus1.register(new SimpleSubscriber2());

        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");
        bus1.post("Hello");

        System.out.println("=========================");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        bus1.post("Hello2","test");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        TimeUnit.SECONDS.sleep(5L);

        Bus bus2 = new EventBus("TestBus",Dispatcher.seqDispatcher(null));

        bus2.register(new SimpleSubscriber1());
        bus2.register(new SimpleSubscriber2());

        bus2.post("Hello");
        bus2.post("Hello");
        bus2.post("Hello");
        bus2.post("Hello");
        bus2.post("Hello");
        bus2.post("Hello");

        System.out.println("=========================");
        bus2.post("Hello2","test");
        bus2.post("Hello2","test");
        bus2.post("Hello2","test");
        bus2.post("Hello2","test");
        bus2.post("Hello2","test");

        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        TimeUnit.SECONDS.sleep(5L);
    }

}
