package com.wangwenjun.concurrency.book27.queue;

import com.wangwenjun.concurrency.book27.message.MethodMessage;

import java.util.LinkedList;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:49
 */
public class ActiveMessageQueue {

    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {

        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage message) {

        synchronized (this) {
            messages.addLast(message);
            this.notify();
        }
    }

    public MethodMessage take() {

        synchronized (this) {
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst();
        }
    }


    private class ActiveDaemonThread extends Thread {

        final ActiveMessageQueue queue;

        public ActiveDaemonThread(ActiveMessageQueue activeMessageQueue) {

            super("ActiveDaemonThread");
            this.queue = activeMessageQueue;
            setDaemon(true);
        }

        @Override
        public void run() {

            for (; ; ) {
                MethodMessage message = this.queue.take();
                message.execute();
            }
        }
    }
}
