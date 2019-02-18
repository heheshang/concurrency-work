package com.wangwenjun.concurrency.third.future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 2:19
 */
public class FutureExample2 {

    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
            return "hello world";
        });

        try {
            Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
            Optional.of(future.get()).ifPresent(System.out::println);
            Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
