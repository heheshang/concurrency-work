package com.wangwenjun.concurrency.third.chapter1;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-21-下午 4:21
 */
public class JITTest {

    private volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {


        new Thread() {
            @Override
            public void run() {

                while (!flag) {
//                    Optional.of("flag is false !!!").ifPresent(System.out::println);
                }
            }
        }.start();


        TimeUnit.SECONDS.sleep(1L);

        new Thread() {

            @Override
            public void run() {

                flag = true;
                Optional.of("flag is true !!!").ifPresent(System.out::println);

            }
        }.start();


    }


}
