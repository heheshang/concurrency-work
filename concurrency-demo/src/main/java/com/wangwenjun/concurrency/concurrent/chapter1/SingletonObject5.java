package com.wangwenjun.concurrency.concurrent.chapter1;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/12 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SingletonObject5 {

    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        //
    }
    // if (null == instance) 时 两个线程执行到此处是 线程1 放弃了cpu执行权，
    // 线程2 获取执行权 线程2会new 一个 instance 线程2 执行完成，线程1 进入执行，
    // 线程1 中 此时也发现 instance 是null 也会进行new instance 操作，
    // 此时就重复出现了多个实例
    // 使用double check方式 解决多线程 创建多个实例的问题，实现了懒加载
    // 加锁最多在两个线程争抢的时候的问题，解决了性能问题
    // 但是可能会引起空指针异常
    // 在instance = new SingletonObject4(); 时候 会在栈中开辟空间，
    // 在初始化操作中private SingletonObject4() 并没有构造完成 的时候返回了instance，
    // 另外的线程进入的时候发现instance 不为null ，就直接返回了。在使用instance 时会造成 null 异常
    // 编译阶段会优化，运行阶段也会优化
    // 解决办法 添加 volatile 关键字
    // volatile 不保证 不保证原子性，但保证 可见性，顺序性，happen‘s before
    //double check add volatile
    public static SingletonObject5 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance)
                    instance = new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }
}
