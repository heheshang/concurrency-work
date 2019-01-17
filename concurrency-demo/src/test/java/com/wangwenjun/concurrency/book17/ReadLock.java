package com.wangwenjun.concurrency.book17;

/**
 * Created by ssk on 2019/1/17 0017.
 * desc:
 */
public class ReadLock implements Lock {


    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {

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

            //若此时有线程在进行写操作，或者有写线程在等待并且偏向写锁的标识为true时，就会无法获取读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.getPreferWriter()
                    && readWriteLock.getWaitingWriters() > 0)
            ) {
                readWriteLock.getMutex().wait();
            }
            // 成功获取读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingWriters();

        }
    }

    /**
     * 释放获取的锁
     */
    @Override
    public void unlock() {

        synchronized (readWriteLock.getMutex()) {

            //释放锁的过程就是使得当前reading的数量减一
            // 将preferWrite设置为true，可以使得writer线程获得更多的机会
            // 通知唤醒Mutex 关联monitor waitset 中的线程
            readWriteLock.decrementReadingWriters();

            readWriteLock.changePrefer(true);
            readWriteLock.getMutex().notifyAll();
        }
    }
}
