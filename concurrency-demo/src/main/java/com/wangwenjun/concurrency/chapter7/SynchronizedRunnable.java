package com.wangwenjun.concurrency.chapter7;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SynchronizedRunnable implements Runnable {

    private int index = 1;

    //readonly shared data.
    private final static int MAX = 500;

    //this
    //    // 使用 synchronized 添加到方法上 相当于使用this 同步代码块形式
    // 在这里使用synchronized 只能保证 一直是一个线程持有锁
    //  使用synchronized 原则是使用尽量小的范围给共享数据加锁
    @Override
    public  void run() {

        while (true) {
            if (ticket())
                break;
        }
    }

    private boolean ticket() {
        synchronized (this) {
            //1. getField
            if (index > MAX)
                return true;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //index++=>index = index+1
            //1. get Field index
            //2. index = index+1
            //3. put field index
            System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
            return false;
        }
    }
}
