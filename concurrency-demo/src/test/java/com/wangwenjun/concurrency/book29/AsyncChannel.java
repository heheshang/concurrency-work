package com.wangwenjun.concurrency.book29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:38
 */
public abstract class AsyncChannel implements Channel<Event> {

    private final ExecutorService executorService;

    public AsyncChannel() {

        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public AsyncChannel(ExecutorService executorService) {

        this.executorService = executorService;
    }

    @Override
    public final void dispatch(Event message) {

        executorService.submit(() -> this.handle(message));
    }

    protected abstract void handle(Event message);

    void stop() {

        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
