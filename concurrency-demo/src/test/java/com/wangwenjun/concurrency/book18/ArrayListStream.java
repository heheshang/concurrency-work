package com.wangwenjun.concurrency.book18;

import java.util.Arrays;
import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-17-下午 12:31
 */
public class ArrayListStream {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("java", "Thread", "concurrency", "scala", "clojure");

        list.parallelStream().map(String::toUpperCase).forEach(System.out::print);

        list.forEach(System.out::print);
    }

}
