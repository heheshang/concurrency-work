package com.wangwenjun.concurrency.book28.listener;

import com.wangwenjun.concurrency.book28.subscribe.Subscribe;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 1:59
 */
public class SimpleObject {

    @Subscribe(topic = "alex-topic")
    public void test2() {

    }

    @Subscribe(topic = "test-topic")
    public void test3() {

    }
}
