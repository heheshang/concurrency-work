package com.wangwenjun.concurrency.third.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 2:35
 */
public class FutureExample4 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "hello world";
        });

        future.whenCompleteAsync((v, e) -> {
            System.out.println("return value:" + v + "  exception:" + e);
        });
        //thenApply方法只是用来处理正常值
        CompletableFuture<String> future3 = future.thenApply((element)->{
            return new Exception();
        }).thenApply((element)->{
            return element+"  addTwoPart";
        });
        future3.whenCompleteAsync((v, e) -> {
            System.out.println("return value:" + v + "  exception:" + e);
        });
        System.out.println(future3.get());//hello world  addPart  addTwoPart


        //入参为原始的CompletableFuture的结果.
        CompletableFuture future4 = future.thenAccept((e)->{
            System.out.println("without return value");
        });
        future4.get();


        CompletableFuture future5 =  future.thenAcceptBoth(CompletableFuture.completedFuture("compose"),
                (x, y) -> System.out.println(x+y));//hello world compose




        Random rand = new Random();
        CompletableFuture<Integer> future9 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future10 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        //两个中任意一个计算完成,那么触发Runnable的执行
        CompletableFuture<String> f =  future10.applyToEither(future9,i -> i.toString());
        //两个都计算完成,那么触发Runnable的执行
        CompletableFuture<Void> f1 = future10.acceptEither(future9,(e)->{
            System.out.println(e);
        });


        CompletableFuture f2= CompletableFuture.allOf(future9,future10);
        System.out.println("======"+f2.get());
    }
}
