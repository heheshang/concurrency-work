package com.wangwenjun.concurrency.book26;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:03
 */
public class WorkerThreadTest {

    public static void main(String[] args) {

        final ProductionChannel channel = new ProductionChannel(5);

        AtomicInteger productionNo = new AtomicInteger();

        IntStream.range(0, 8).forEach(i -> new Thread(() -> {
                    while (true) {
                        channel.offerProduction(new Production(productionNo.getAndIncrement()));

                        try {
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start()

        );
    }
}
