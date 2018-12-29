package com.wangwenjun.concurrency.chapter2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class BankVersion2 {

    private final static int MAX = 50;

    public static void main(String[] args) {
        //Runnable 接口的作用是将线程控制单元和业务逻辑拆分 类似于策略模式
        //Runnable 接口的run方法支持实现线程的执行单元，真正执行线程的是Thread start0方法
//        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        // int index 在每个线程中都是独立的变量，是线程私有的
        final Runnable ticketWindow = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
