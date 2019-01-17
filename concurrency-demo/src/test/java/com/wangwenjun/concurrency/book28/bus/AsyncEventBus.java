package com.wangwenjun.concurrency.book28.bus;

import com.wangwenjun.concurrency.book28.EventExceptionHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 11:04
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(String busName, EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {

        super(busName, eventExceptionHandler, executor);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {

        super(busName, null, executor);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {

        super("default-async", null, executor);
    }

    public AsyncEventBus(EventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor) {

        super("default-async", eventExceptionHandler, executor);
    }

}
