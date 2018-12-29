package com.wangwenjun.concurrency.book25;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 2:47
 */
@FunctionalInterface
public interface CacheLoader<K, V> {
    V load(K k);
}
