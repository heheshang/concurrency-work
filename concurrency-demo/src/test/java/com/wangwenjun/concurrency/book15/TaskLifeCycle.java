package com.wangwenjun.concurrency.book15;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 4:03
 */
public interface TaskLifeCycle<T> {

    /**
     * 启动任务时会出发onStart 方法
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning 方法
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时会触发onFinish方法，其中result是任务执行后的结果
     * @param thread
     */
    void onFinish(Thread thread,T result);

    /**
     * 任务执行报错时会触发onError 方法
     * @param thread
     * @param e
     */
    void onError(Thread thread, Exception e);

    /**
    * 生命周期接口的空实现
    * @author ssk www.8win.com Inc.All rights reserved
    * @date 2019/01/16 下午 4:09
    * @since v1.0
    **/
    class EmptyLifeCycle<T> implements TaskLifeCycle<T>{



        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
