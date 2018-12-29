package com.wangwenjun.concurrency.book19;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 9:47
 */
public class FutureTask<T> implements Future<T> {

    private T result;

    private boolean isDone = false;

    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {

        synchronized (LOCK) {

            while (!isDone) {
                LOCK.wait();
            }

            return result;
        }
    }

    @Override
    public boolean done() {

        return isDone;
    }


    protected void finish(T result) {

        synchronized (LOCK) {

            if (isDone) {
                return;
            }

            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }
}
