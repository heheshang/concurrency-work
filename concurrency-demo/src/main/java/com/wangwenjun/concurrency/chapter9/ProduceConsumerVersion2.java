package com.wangwenjun.concurrency.chapter9;

import java.util.stream.Stream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ProduceConsumerVersion2 {

    private int i = 0;

    /**
     *  The current thread must own this object's monitor. The thread
     *      * releases ownership of this monitor and waits until another thread
     *      * notifies threads waiting on this object's monitor to wake up
     *      * either through a call to the {@code notify} method or the
     *      * {@code notifyAll} method. The thread then waits until it can
     *      * re-obtain ownership of the monitor and resumes execution.
     */
    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    // wait 之后会放弃cpu 的执行权 等待其他线程唤醒
                    LOCK.wait();
//                    System.out.println(Thread.currentThread().getName()+"====="+Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
//                System.out.println("C->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {

                    LOCK.wait();
//                    System.out.println(Thread.currentThread().getName()+"====="+Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        // 在单生产者单消费者使用时 使用notify 没有问题 ，
        // 但是在使用多生产者多消费者情况下会出现线程互相等待的情况（并没有出现死锁）
        // notify  只是唤醒一个线程获得cpu 的执行权，如果是c2 获得了cpu执行权，c2 发现并没有可消费的数据，会再一次进入到wait 状态


        Stream.of("P1", "P2").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true)
                            pc.produce();
                    }
                }.start()
        );
        Stream.of("C1", "C2").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true)
                            pc.consume();
                    }
                }.start()
        );
    }
}