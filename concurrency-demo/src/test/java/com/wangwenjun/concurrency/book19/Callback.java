package com.wangwenjun.concurrency.book19;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:37
 */
@FunctionalInterface
public interface Callback<T> {

    void call(T t);
}
