package com.wangwenjun.concurrency.third.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 3:12
 */
public class FutureExample5 {

    static CompletableFuture<String> findAccount(String accountId) {

        return CompletableFuture.supplyAsync(() -> {
            // mock finding account from database
            return "account" + accountId;
        });
    }

    public static void batchProcess(List<String> accountIdList) throws InterruptedException {
        // 并行根据accountId查找对应account
        List<CompletableFuture<String>> accountFindingFutureList =
                accountIdList.stream().map(accountId -> findAccount(accountId)).collect(Collectors.toList());

        // 使用allOf方法来表示所有的并行任务
        CompletableFuture<Void> allFutures =
                CompletableFuture
                        .allOf(accountFindingFutureList.toArray(new CompletableFuture[accountFindingFutureList.size()]));

        // 下面的方法可以帮助我们获得所有子任务的处理结果
        CompletableFuture<List<String>> finalResults = allFutures.thenApply(v -> {
            return accountFindingFutureList.stream().map(accountFindingFuture -> accountFindingFuture.join())
                    .collect(Collectors.toList());
        });

        CompletableFuture<List<String>> listCompletableFuture = finalResults.whenCompleteAsync((v, e) -> {
            v.forEach(s -> System.out.println(s.toString() + e));
        });

//        listCompletableFuture.whenComplete((res, ex) -> {
//            if (null == ex) {
//                System.out.println("result from previous task: " + ex);
//            } else {
//                System.out.println("sss from previous task: " + res);
//            }
//        });
    }

    public static void main(String[] args) throws InterruptedException {

        batchProcess(Arrays.asList("a", "b", "c"));
    }

}
