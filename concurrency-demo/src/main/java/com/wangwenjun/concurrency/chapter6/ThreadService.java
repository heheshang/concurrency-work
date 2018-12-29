package com.wangwenjun.concurrency.chapter6;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                // 这里讲 runnerThread 设置为守护线程的目的是
                // 在 executeThread 执行中断操作时也随之退出
                runner.setDaemon(true);

                runner.start();
                try {
                    /**
                     * 在很多情况下，主线程生成并起动了子线程，
                     * 如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，
                     * 但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，
                     * 也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。
                     *
                     * JDK中对join方法解释为：“等待该线程终止”，
                     * 换句话说就是：”当前线程等待子线程的终止“。
                     * 也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了当前线程才能执行。
                     */
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        };

        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束他!");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断!");
                break;
            }
        }

        finished = false;
    }
}