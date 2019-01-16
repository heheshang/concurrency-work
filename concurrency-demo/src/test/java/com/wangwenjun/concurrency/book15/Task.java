package com.wangwenjun.concurrency.book15;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 4:09
 */
@FunctionalInterface
public interface Task<T> {

    //任务执行接口，该接口允许有返回值
    T call();
}
