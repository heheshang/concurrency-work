package com.wangwenjun.concurrency.book15;

import java.util.concurrent.TimeUnit;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 4:32
 */
public class ObservableTest {

    public static void main(String[] args) {

     /*   Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(" finished done");

            return null;
        });

        observableThread.start();*/


        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle.EmptyLifeCycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {

                System.out.println("The result is " + result);
            }
        };

        Observable observableThread = new ObservableThread<>(lifeCycle, () -> {

            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(" finished done");

            return " Hello  observer";
        });

        observableThread.start();
    }

}
