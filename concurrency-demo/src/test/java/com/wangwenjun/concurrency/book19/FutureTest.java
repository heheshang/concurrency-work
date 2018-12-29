package com.wangwenjun.concurrency.book19;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 10:05
 */
public class FutureTest {


    public static void main(String[] args) throws InterruptedException {

        FutureService<Void, Void> service = FutureService.newService();

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Optional.of("I am finish done.").ifPresent(System.out::println);

        });

        future.get();

        FutureService<String, Integer> service1 = FutureService.newService();

        Future<?> future1 = service1.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();

        }, "Hello");

        Optional.of(future1.get()).ifPresent(System.out::println);
    }
}
