package com.wangwenjun.concurrency.book17;

/**
 * Created by ssk on 2019/1/17 0017.
 * desc:
 */
public class WriteLock implements Lock {


    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {

        this.readWriteLock = readWriteLock;
    }

    /**
     * 获取显示锁，没有获取锁的线程将被阻塞
     *
     * @throws java.lang.InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {

        //使用Mutex作为锁
        synchronized (readWriteLock.getMutex()) {
            //首先使等待获取写入锁的数字加1
            readWriteLock.incrementWaitingWriters();
            //若此时有线程在进行读操作，或者写操作，那么当前线程将被挂起
            try {

                while (readWriteLock.getReadingReaders() > 0
                        | readWriteLock.getWaitingWriters() > 0) {
                    readWriteLock.getMutex().wait();
                }

            } finally {
                // 成功获取到了写入锁，使得等待获取写入锁的计数器减1
                this.readWriteLock.decrementWaitingWriters();
            }
            // 将正在写入的线程数量加1
            readWriteLock.incrementWritingWriters();

        }
    }

    /**
     * 释放获取的锁
     */
    @Override
    public void unlock() {

        synchronized (readWriteLock.getMutex()) {

            //减少正在写入锁的线程计数器
            // 将preferWrite设置为false，可以使得该读锁被最快速的获得
            // 通知唤醒Mutex 关联monitor waitset 中的线程
            readWriteLock.decrementWritingWriters();

            readWriteLock.changePrefer(false);
            readWriteLock.getMutex().notifyAll();
        }
    }
}
