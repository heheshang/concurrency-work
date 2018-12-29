package com.wangwenjun.concurrency.concurrent.chapter3;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/18 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class VolatileTest2 {

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;
    // ram  Random Access Memory
    // 随机存取存储器（英语：Random Access Memory，缩写：RAM），也叫主存，是与CPU直接交换数据的内部存储器。[1]它可以随时读写（刷新时除外，见下文），而且速度很快，通常作为操作系统或其他正在运行中的程序的临时数据存储介质
    // rom 系统

    // 解决数据不一致的办法
    // 1 给数据总线加锁（数据总线，地址总线，控制总线） LOCK#
    // 2 cpu 高速缓存一致性协议 intel mesi
    //  处理器上有一套完整的协议，来保证Cache一致性。比较经典的Cache一致性协议当属MESI协议，奔腾处理器有使用它，很多其他的处理器都是使用它的变种。
    //
    // 单核Cache中每个Cache line有2个标志：dirty和valid标志，它们很好的描述了Cache和Memory(内存)之间的数据关系(数据是否有效，数据是否被修改)，
    // 而在多核处理器中，多个核会共享一些数据，MESI协议就包含了描述共享的状态。
    //
    //在MESI协议中，每个Cache line有4个状态，可用2个bit表示，它们分别是： 
    //
    //
    // M(Modified)  这行数据有效，数据被修改了，和内存中的数据不一致，数据只存在于本Cache中。
    // E(Exclusive) 这行数据有效，数据和内存中的数据一致，数据只存在于本Cache中。
    // S(Shared)    这行数据有效，数据和内存中的数据一致，数据存在于很多Cache中。
    // I(Invalid)   这行数据无效。
    // 在MESI协议中，每个Cache的Cache控制器不仅知道自己的读写操作，而且也监听(snoop)其它Cache的读写操作。
    // 每个Cache line所处的状态根据本核和其它核的读写操作在4个状态间进行迁移。
    // ★★★★★★★核心思想★★★★★★★★★
    // ★★1.当cpu 写入数据的时候，如果发现改变量被共享（也就是说，在其他的cpu中也存在改变量的副本），
    // 会发出一个信号，通知其他cpu该变量的保存无效★★
    // ★★2.当其他cpu访问该变量重新到主内存进行获取
    public static void main(String[] args) {

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                int localValue = INIT_VALUE;
                localValue++;
                INIT_VALUE = localValue;
                System.out.println("T1->" + localValue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                int localValue = INIT_VALUE;
                localValue++;
                INIT_VALUE = localValue;
                System.out.println("T2->" + localValue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();
    }


}
/**
 * 并发编程中三个比较重要的概念
 *
 * 1.原子性A
 * 	i = 9 (16/16)
 *
 * 一个操作或者多个操作，要么都成功，要么都失败，中间不能由于任何的因素终端
 *
 * 2.可见性V
 *
 * Thread-1		Thread-2
 *
 * int i = 0;		int j = i;
 *
 * i = 10;
 *
 * cache			cache
 * (i=10)			j=0(maybe)
 *
 *
 * i=10			j=10
 *
 * 3.有序性（顺序性）O
 * {
 * 	int i =0	（1）
 * 	i=1;		（2）
 * 	boolean flag = false;（3）
 * 	flag = true;	（4）
 * }
 *
 * i=1;
 * flag = true;
 * 重排序只要求最终一致性
 *
 * volatile boolean init;
 *
 * ---------Thread -1------------
 *
 * obj = createObj()	1;
 * init = true;		2;
 *
 * ---------Thread -2-----------
 * while(!init){
 * 	sleep();
 * }
 *
 * useTheObj(obj);
 * ----------------------------
 *
 * 1.原子性
 * 对基本数据类型的变量读取和赋值是保证了原子性的，要么都成功，要么都失败，这些操作不可被中断
 *
 * i = 10;
 * cache 10, memory 10
 *
 * a=10;	原子性
 * b=a;	不满足，1.read a;2.assign b;
 * c++;	不满足, 1.read c;2.add;3.assign to c;
 * c=c+1;  不满足, 1.read c;2.add;3.assign to c;
 *
 * 2.可见性
 * 	使用volatile关键字保证可见性；
 *
 * 3.有序性
 * 	happens-before relationship
 *
 * 3.1 代码的执行顺序，编写在前面的发生在编写在后面的
 * 3.2 unlock必须发正在lock之后
 * 3.3 volatile修饰的变量，对一个变量的写操作先于对该变量的读操作。
 * 3.4 传递规则，操作A先于B，B先于C，那么A肯定先于C
 * 3.5 线程启动规则，start方法肯定先于线程run
 * 3.6 线程中断规则，interrupt这个动作，必须发生在捕获该动作之前
 * 3.7 对象销毁规则，初始化必须发生在finalize之前
 * 3.8 线程终结规则，所有的操作都发生在线程死亡之前
 * try{
 * 	lock.lock();
 * }finally{
 * 	lock.unlock();
 * }
 *
 * //------1
 * synchronized(obj){
 *
 * }
 * //------2
 * volatile关键字
 * 一旦一个共享变量被volatile修饰，具备两层语义
 * 1.保证了不同线程间的可见性
 * 2.禁止对其进行重排序，也就是保证了有序性
 * 3.并未保证原子性
 *
 * new Thread(() -> {
 *             while (INIT_VALUE < MAX_LIMIT) {
 *                 System.out.println("T1->" + (++INIT_VALUE));
 *                 try {
 *                     Thread.sleep(10);
 *                 } catch (InterruptedException e) {
 *                     e.printStackTrace();
 *                 }
 *             }
 *         }, "ADDER-1").start();
 *
 *         new Thread(() -> {
 *             while (INIT_VALUE < MAX_LIMIT) {
 *                 System.out.println("T2->" + (++INIT_VALUE));
 *                 try {
 *                     Thread.sleep(10);
 *                 } catch (InterruptedException e) {
 *                     e.printStackTrace();
 *                 }
 *             }
 *         }, "ADDER-1").start();
 *
 *
 * 10
 * 1.read from main memory INIT_VALUE->10
 * 2.INIT_VALUE=10+1
 * 3.INIT_VALUE=11
 *
 * Volatile关键字
 * 1.保证重排序的是偶不会把后面的指令放到屏障的前面，也不会把前面的放到后面
 * 2.强制对缓存的修改操作立刻写入主存
 * 3.如果是写操作，他会导致其他CPU中的缓存失效
 *
 * volatile的使用场景
 * 1.状态量标记
 * volatile boolean start = true;
 * while(start){
 * 	//
 * }
 *
 * void close(){
 * 	start = false;
 * }
 *
 * 2.屏障前后的一致性
 *
 * volatile boolean init;
 *
 * ---------Thread -1------------
 * //...........
 * obj = createObj()	1;
 * init = true;		2;
 *
 * ---------Thread -2-----------
 * while(!init){
 * 	sleep();
 * }
 *
 * useTheObj(obj);
 * ----------------------------
 * 1.cpu的大致结构
 * 2.JMM的大致结构
 * 3.缓存一致性协议
 * 4.指令重排序
 * 5.happens-before规则
 * 6.并发编程的三大要素
 * 7.volatile关键字的作用
 */


