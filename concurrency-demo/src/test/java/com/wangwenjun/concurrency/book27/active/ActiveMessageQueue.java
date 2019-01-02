package com.wangwenjun.concurrency.book27.active;

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

    private final LinkedList<ActiveMessage> messages = new LinkedList<>();

    public ActiveMessageQueue() {

        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage message) {

        synchronized (this) {
            messages.addLast(message);
            this.notify();
        }
    }

    public ActiveMessage take() {

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
                ActiveMessage message = this.queue.take();
                message.execute();
            }
        }
    }
}
