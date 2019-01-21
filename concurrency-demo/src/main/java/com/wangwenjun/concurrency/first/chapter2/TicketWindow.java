package com.wangwenjun.concurrency.first.chapter2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class TicketWindow extends Thread {

    private final String name;

    private static final int MAX = 500;

    /**
     * static变量也称作静态变量，
     * 静态变量和非静态变量的区别是：静态变量被所有的对象所共享，
     * 在内存中只有一个副本，它当且仅当在类初次加载时会被初始化。
     * 而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
     *
     * static成员变量的初始化顺序按照定义的顺序进行初始化。
     *
     * ★★★但是在Java中切记：static是不允许用来修饰局部变量。不要问为什么，这是Java语法的规定。★★★
     */
    private  static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while (index <= MAX) {

            System.out.println("柜台：" + name + "当前的号码是:" + (index++));
        }
    }
}
