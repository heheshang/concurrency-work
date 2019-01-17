package com.wangwenjun.concurrency.book17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-17-上午 10:46
 */
public class ShareData {

    private final List<Character> container = new ArrayList<>();

    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {

        this.length = length;

        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {

        try {
            readLock.lock();
            char[] newBuffer = new char[length];

            IntStream.range(0, length).forEach(i -> newBuffer[i] = container.get(i));
            slowly();

            return newBuffer;
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {

        try {
            writeLock.lock();

            IntStream.range(0, length).forEach(i -> this.container.add(i, c));
            slowly();

        } finally {
            writeLock.unlock();
        }
    }


    private void slowly() {

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
