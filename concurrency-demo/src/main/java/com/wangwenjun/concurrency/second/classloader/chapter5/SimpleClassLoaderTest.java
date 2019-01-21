package com.wangwenjun.concurrency.second.classloader.chapter5;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/4 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SimpleClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("java.lang.String");
        Class<?> bClass = simpleClassLoader.loadClass("com.wangwenjun.concurrency.second.classloader.chapter5.SimpleObject");
        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
    }
}
