package com.wangwenjun.concurrency.book28;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:02
 */
public class SimpleSubscriber2 {

    @Subscribe
    public void method1(String message) {

        System.out.println(Thread.currentThread().getName()+" == SimpleSubscriber2 ==method1== " + message);
    }
    @Subscribe(topic = "test")
    public void method2(String message){
        System.out.println(Thread.currentThread().getName()+" == SimpleSubscriber2 ==method2== " + message);
    }
}
