package com.wangwenjun.concurrency.chapter12;

import java.util.Arrays;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/25 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ThreadGroupAPI {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 一个线程组表示一组线程。此外，一个线程组还可以包括其他线程组。
         * 线程组形成一个树，其中每一个线程组除了初始线程组有一个父。
         * 允许一个线程访问它自己的线程组的信息，
         * 但不允许访问它的线程组的父线程组或其他任何线程组的信息。
         */
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {

//                while (true) {
                    try {
                        //sleep 的时候不会放弃 cpu执行权，
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
//                        break;
                    }
//                }
            }
        };
        /**
         * 改变该线程组的守护状态。
         * 首先，该线程组的checkAccess方法不带参数调用；这可能导致安全异常。
         *
         * 守护线程组时自动撤销其最后一个线程停止或最后一个线程组被破坏。
         */
//        tg1.setDaemon(true);
        t1.start();
        Thread.sleep(2_000);
        System.out.println(tg1.isDestroyed());
        tg1.destroy();
        System.out.println(tg1.isDestroyed());

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };

        t2.start();
//
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
//        t2.checkAccess();
//       tg1.destroy();
//
        /**
         * 在这个线程组中的每一个活动线程中复制到指定的数组中。
         * 如果 recurse是 true，此方法递归枚举所有子群该线程组和引用这些分组中的所有活动线程也包括在内。
         * 如果数组太短，不保留所有的线程，额外的线程将被忽略。
         * 一个应用程序可能使用activeCount方法得到的估计有多大的数组是应该的，
         * 但是空如果数组太短，不保留所有的线程，额外的线程将被忽略。
         * 如果是该线程组中的每一个活动线程获得关键，调用者应该验证返回int值严格小于list长度。
         *
         * 由于在这种方法中的固有的比赛条件，它是建议，该方法只用于调试和监控的目的。
         */
        System.out.println("=========================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println("=========================");
        tg1.enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);
//
        System.out.println("=========================");
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, false);
        Arrays.asList(ts1).forEach(System.out::println);
        /**
         * 中断这个线程组中的所有线程。
         * 首先，该线程组的checkAccess方法不带参数调用；这可能导致安全异常。
         *
         * 这个方法然后调用该线程组中，在其所有亚组的所有线程的interrupt方法。
         */
//        tg1.interrupt();

    }
}
