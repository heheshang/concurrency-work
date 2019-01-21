package com.wangwenjun.concurrency.second.concurrent.chapter7;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/22 QQ:532500648
 * QQ交流群:286081824
 ***************************************/

/**
 * 不可变对象一定是线程安全的 里面的属性或者引用类型都不可被修改
 * 可变对象不一定是线程不安全的 ex:StringBuffer
 *
 * servlet 不是线程安全的，每次请求都是生成一个servlet 实例
 * struts 1.x 的action 也不是线程安全的
 * struts 2.x 的action 是线程安全的 对每一次的请求都会创建一个action
 */
final public class Person {

    private final String name;

    private final String address;

    public Person(final String name, final String address) {

        this.name = name;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public String getAddress() {

        return address;
    }

    @Override
    public String toString() {

        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}