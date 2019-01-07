package com.wangwenjun.concurrency.concurrent.chapter9;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/23 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {

        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
        /**
         *  JDK中对join方法解释为：“等待该线程终止”，
         *  换句话说就是：”当前线程等待子线程的终止“。
         *  也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了当前线程才能执行
         */
//        serverThread.join();

        Thread.sleep(10000);
        serverThread.close();
    }
}
