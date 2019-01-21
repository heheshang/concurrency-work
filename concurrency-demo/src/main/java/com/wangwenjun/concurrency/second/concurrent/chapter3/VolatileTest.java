package com.wangwenjun.concurrency.second.concurrent.chapter3;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/18 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class VolatileTest {

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    // 内存 cache 的出现主要是加快cpu 的处理速度，但是也引发了缓存不同步的问题
                    // 这也是 L1 L2 L3 三级缓存的由来，L1 中又分ld:数据cache li：指令级的cache 越里cpu近越快
                    // 第一级高速缓存（L1）–通常访问只需要几个周期，通常是几十个KB。
                    // 第二级高速缓存（L2）–比L1约有2到10倍较高延迟性，通常是几百个KB或更多。
                    // 第三级高速缓存（L3）（不一定有）–比L2更高的延迟性，通常有数MB之大。
                    // 第四级高速缓存（L4）（不普遍）–CPU外部的DRAM，但速度较主存高。
                    // 在注释掉volatile 关键字的时候 此行代码不会打印，
                    // 是由于jvm 优化的时候 发现这个线程中发现只有读的操作，
                    // 故而不会去主内存中拿最新的INIT_VALUE 值
                    // 因为每个cpu 都 管理一部分属于cpu 的内存
                    // 加入volatile 后 cpu 会监测到 INIT_VALUE 值的变化，jvm 会强制去主内存中同步数据
                    // 故而 会打印信息
                    //
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }
}