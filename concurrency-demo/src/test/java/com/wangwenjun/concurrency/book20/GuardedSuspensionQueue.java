package com.wangwenjun.concurrency.book20;

import java.util.LinkedList;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 10:23
 */
public class GuardedSuspensionQueue {


    private final LinkedList<Integer> queue = new LinkedList<>();

    private final int LIMIT = 100;

    public void offer(Integer data) {

        synchronized (this) {

            while (queue.size() >= LIMIT) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                queue.addLast(data);
                this.notifyAll();
            }
        }
    }

    public Integer take() throws InterruptedException {

        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
