package com.wangwenjun.concurrency.book26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 3:49
 */
public class Worker extends Thread {


    private final ProductionChannel channel;

    public Worker(String workerName, ProductionChannel productionChannel) {

        super(workerName);
        this.channel = productionChannel;


    }

    @Override
    public void run() {

        while (true) {
            try {
                Production production = channel.takeProduction();

                production.create();

                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
