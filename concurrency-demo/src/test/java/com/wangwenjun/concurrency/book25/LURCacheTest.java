package com.wangwenjun.concurrency.book25;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 2:54
 */
public class LURCacheTest {

    public static void main(String[] args) {

//        LURCache<String, Reference> cache = new LURCache<>(5, key -> new Reference());
//
//        cache.get("a");
//        cache.get("b");
//        cache.get("c");
//        cache.get("d");
//        cache.get("e");
//
//        cache.get("f");

//        System.out.println(cache.toString());

/*
        LURCache<Integer, Reference> cache = new LURCache<>(200, key -> new Reference());

        IntStream.rangeClosed(0, Integer.MAX_VALUE).forEach(
                i -> {
                    cache.get(i);
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                        Optional.of("The " + i + " reference stored at cache").ifPresent(System.out::println);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );*/


        SoftLURCache<Integer, Reference> softLURCache = new SoftLURCache<>(1000, key -> new Reference());
        System.out.println(softLURCache);
        IntStream.rangeClosed(0, Integer.MAX_VALUE).forEach(
                i -> {
                    softLURCache.get(i);
//                    try {
////                        TimeUnit.SECONDS.sleep(1L);
//                        Optional.of("The " + i + " reference stored at cache").ifPresent(System.out::println);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
        );
    }

}
