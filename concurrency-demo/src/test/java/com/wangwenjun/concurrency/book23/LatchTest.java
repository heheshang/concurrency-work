package com.wangwenjun.concurrency.book23;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 1:16
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException, WaitTimeOutException {

        Latch latch = new CountDownLatch(4);

        new ProgrammerTravel(latch, "a", "1").start();
        new ProgrammerTravel(latch, "b", "2").start();
        new ProgrammerTravel(latch, "c", "3").start();
        new ProgrammerTravel(latch, "d", "4").start();

//        latch.await();
        latch.await(TimeUnit.SECONDS, 5L);
        Optional.of("=== all of programmer arrived .").ifPresent(System.out::println);

    }

}
