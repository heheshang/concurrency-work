package com.wangwenjun.concurrency.concurrent.chapter1;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/12 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }
    // if (null == instance) 时 两个线程执行到此处是 线程1 放弃了cpu执行权，
    // 线程2 获取执行权 线程2会new 一个 instance 线程2 执行完成，线程1 进入执行，
    // 线程1 中 此时也发现 instance 是null 也会进行new instance 操作，
    // 此时就重复出现了多个实例
    // 加入 class 锁 synchronized 解决多线程 创建多个实例的问题，实现了懒加载
    // 但是每次调用都进行synchronized 操作，影响了性能，使得并行操作 变成了串行操作

    public synchronized static SingletonObject3 getInstance() {

        if (null == instance)
            instance = new SingletonObject3();

        return SingletonObject3.instance;
    }
}