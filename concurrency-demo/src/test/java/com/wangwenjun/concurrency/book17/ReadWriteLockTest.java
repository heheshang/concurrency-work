package com.wangwenjun.concurrency.book17;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-17-上午 11:18
 */
public class ReadWriteLockTest {

    private final static String text = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {

        final ShareData shareData = new ShareData(50);

        IntStream.range(0, 2).forEach(i -> {

            new Thread(() -> {

                IntStream.range(0, text.length()).forEach(
                        index -> {
                            try {
                                char c = text.charAt(index);
                                shareData.write(c);
                                Optional.of(Thread.currentThread() + " write " + c).ifPresent(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                );

            }, "lock-write-Thread" + i).start();
        });


        IntStream.range(0, 10).forEach(i -> {

            new Thread(() -> {
                while (true) {
                    try {
                        Optional.of(Thread.currentThread() + " read " + new String(shareData.read())).ifPresent(System.out::println);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }


}
