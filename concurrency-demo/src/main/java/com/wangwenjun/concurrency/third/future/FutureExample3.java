package com.wangwenjun.concurrency.third.future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 2:24
 */
public class FutureExample3 {

    public static CompletableFuture<Integer> compute() {

        final CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        return future;
    }

    public static void main(String[] args) throws InterruptedException {

        final CompletableFuture<Integer> f = compute();

        class Client extends Thread {

            CompletableFuture<Integer> f;

            public Client(String threadName, CompletableFuture<Integer> f) {

                super(threadName);
                this.f = f;
            }

            @Override
            public void run() {

                try {
                    Optional.of(this.getName() + " :" + f.get()).ifPresent(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        new Client("Client1", f).start();
        new Client("Client2", f).start();

        Optional.of("waiting").ifPresent(System.out::println);

//        f.complete(100);
        //以异常的形式触发计算
        f.completeExceptionally(new Exception());
        Thread.sleep(1000);
    }
}
