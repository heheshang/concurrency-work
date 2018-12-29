package com.wangwenjun.concurrency.book25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 2:45
 */
public class LURCache<K, V> {

    private final LinkedList<K> keyList = new LinkedList<>();

    private final Map<K, V> cache = new HashMap<>();

    private final int capacity;

    private final CacheLoader<K, V> cacheLoader;

    public LURCache(int capacity, CacheLoader<K, V> cacheLoader) {

        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value) {

        if (keyList.size() >= capacity) {
            K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }

        if (keyList.contains(key)) {
            keyList.remove(key);
        }
        keyList.addLast(key);
        cache.put(key, value);
    }

    public V get(K key) {

        V value;
        boolean success = keyList.remove(key);
        if (!success) {
            value = cacheLoader.load(key);
            this.put(key, value);
        } else {
            value = cache.get(key);
            keyList.addLast(key);
        }

        return value;
    }

    @Override
    public String toString() {

        return this.keyList.toString();
    }
}
