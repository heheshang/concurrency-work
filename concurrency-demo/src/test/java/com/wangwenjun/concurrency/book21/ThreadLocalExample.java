package com.wangwenjun.concurrency.book21;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 11:01
 */
public class ThreadLocalExample {

    public static void main(String[] args) {

        ThreadLocal<Integer> local = new ThreadLocal<>();

        IntStream.rangeClosed(0, 10).forEach(
                i -> new Thread(
                        () -> {
                            local.set(i);
                            try {

                                Optional.of(Thread.currentThread() + " set i " + local.get()).ifPresent(System.out::println);
                                TimeUnit.SECONDS.sleep(1L);
                                Optional.of(Thread.currentThread() + " get i " + local.get()).ifPresent(System.out::println);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }).start()

        );


        ThreadLocal<Object> local1 = ThreadLocal.withInitial(Object::new);

        new Thread(() -> {
            Optional.of(local1.get()).ifPresent(System.out::println);
        }).start();

        Optional.of(local1.get()).ifPresent(System.out::println);

    }

}
