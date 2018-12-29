package com.wangwenjun.concurrency.book26;


import java.util.Optional;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 3:47
 */
public class ProductionChannel {

    private final static int MAX_PROD = 100;

    private final Production[] productionQueue;

    private int tail;

    private int head;

    private int total;

    private final Worker[] workers;


    public ProductionChannel(int workerSize) {

        this.workers = new Worker[workerSize];

        this.productionQueue = new Production[MAX_PROD];

        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("Worker-" + i, this);
            workers[i].start();
        }
    }

    public void offerProduction(Production production) {

        synchronized (this) {
            while (total >= productionQueue.length) {

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            Optional.of(Thread.currentThread().getName() + " offer production ").ifPresent(System.out::println);

            this.notifyAll();
        }


    }


    public Production takeProduction() {

        synchronized (this) {
            while (total <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Production prod = productionQueue[head];

            head = (head + 1) % productionQueue.length;

            total--;

            this.notifyAll();
            Optional.of(Thread.currentThread().getName() + " take production ").ifPresent(System.out::println);
            return prod;
        }
    }
}
