package com.wangwenjun.concurrency.book19;


/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 9:43
 */
public interface FutureService<IN, OUT> {

    Future<?> submit(Runnable runnable);

    Future<?> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);


    static <T, R> FutureService<T, R> newService() {

        return new FutureServiceImpl();
    }
}
