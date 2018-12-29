package com.wangwenjun.concurrency.book23;

import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 1:03
 */
public abstract class Latch {

    protected int limit;

    public Latch(int limit) {

        this.limit = limit;
    }

    public abstract void await() throws InterruptedException;

    public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeOutException;

    public abstract void countDown();

    public abstract int getUnarrived();
}
