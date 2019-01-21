package com.wangwenjun.concurrency.first.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月26日 上午 10:46
 */
public class SimpleThreadPool1 extends Thread {

    // 定义线程池大小
    private int size;

    private final static int DEFAULT_SIZE = 10;

    // 线程队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static volatile int seq = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private final int queueSize;

    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    private int min;

    private int max;

    private int active;

    public final static DiscardPolicy defaultDiscardPolicy = () -> {
        throw new DiscardException("Discard this task.");
    };

    public SimpleThreadPool1() {

        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, defaultDiscardPolicy);
    }

    public SimpleThreadPool1(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {

        this.min = min;
        this.max = max;
        this.active = active;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;

        init();
    }

    private void init() {

        for (int i = 0; i < min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    public int getSize() {

        return size;
    }

    public int getQueueSize() {

        return queueSize;
    }

    public int getMin() {

        return min;
    }

    public int getMax() {

        return max;
    }

    public int getActive() {

        return active;
    }

    public boolean isDestroy() {


        return this.destroy;
    }

    public void submit(Runnable runnable) {

        if (destroy) {
            throw new IllegalStateException("the thread pool already destroy and not allow submit task.");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();

        }
    }

    public void shutdown() throws InterruptedException {

        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKING) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;

        System.out.println(GROUP.activeCount());
        System.out.println("The thread pool disposed.");
    }

    @Override
    public void run() {

        while (!destroy) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());

            try {
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }

                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=========Reduce========");
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0)
                                break;

                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkTask(/*String workerName*/) {

        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKING, DEAD;
    }

    public interface DiscardPolicy {

        void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;


        public WorkerTask(ThreadGroup group, String name) {

            super(group, name);
        }

        public TaskState getTaskState() {

            return taskState;
        }

        public void setTaskState(TaskState taskState) {

            this.taskState = taskState;
        }


        @Override
        public void run() {

            OTHER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKING;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + "Closed.");
                            break OTHER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;

                }
            }
        }

        public void close() {

            this.taskState = TaskState.DEAD;
        }
    }


    public static class DiscardException extends RuntimeException {

        public DiscardException(String message) {

            super(message);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        SimpleThreadPool1 threadPool = new SimpleThreadPool1(/*6, 10, SimpleThreadPool1.defaultDiscardPolicy*/);

        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable  be serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread() + " finished.");
            });
        }

//        Thread.sleep(10000L);
//        threadPool.shutdown();
        Thread.sleep(10_000L);
        threadPool.shutdown();
//        threadPool.submit(() -> System.out.println("======="));
    }
}
