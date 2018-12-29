package com.wangwenjun.concurrency.book23;

import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 1:06
 */
public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {

        super(limit);
    }

    @Override
    public void await() throws InterruptedException {

        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeOutException {

        if (time < 0) {
            throw new IllegalArgumentException("The time id invalid");
        }

        long remainingNanos = unit.toNanos(time);

        final long endNanos = System.nanoTime() + remainingNanos;

        synchronized (this) {
            while (limit > 0) {
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimeOutException("The wait time over specify time.");

                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }

    }

    @Override
    public void countDown() {

        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived .");
            }

            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {

        return limit;
    }
}
