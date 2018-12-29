package com.wangwenjun.concurrency.concurrent.chapter1;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/12 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {
        //empty
    }

    public static SingletonObject2 getInstance() {
        if (null == instance)// 两个线程执行到此处是 线程1 放弃了cpu执行权，
            // 线程2 获取执行权 线程2会new 一个 instance 线程2 执行完成，线程1 进入执行，
            // 线程1 中 此时也发现 instance 是null 也会进行new instance 操作，
            // 此时就重复出现了多个实例
            instance = new SingletonObject2();

        return SingletonObject2.instance;
    }
}
