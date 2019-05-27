package com.wangwenjun.concurrency.clrs.chap02;

import java.util.Arrays;

/**
 * 递归方式的插入排序
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-03-13-上午 11:07
 */
public class InsertSortRecursive {

    public void insertSortRecursive(int[] src, int end) {
        //也可以把这个判断条件改成 : if(end > 0) 加到下面的“insertSort_Recursive(src, end - 1);"前面
        if (end < 0) {
            return;
        }

        insertSortRecursive(src, end - 1);

        merge(src, end);
    }

    /**
     * 新增加出来的方法，一个数字前面的数组中的元素都有序，将这个数字放到数组中合适的位置
     *
     * @param src
     * @param end
     */
    private void merge(int[] src, int end) {

        if (src.length == 0 || src.length == 1) {
            return;
        }
        int key = src[end];
        int i = end - 1;
        while (i >= 0 && src[i] > key) {
            src[i + 1] = src[i--];
            System.out.println(Arrays.toString(src));
        }
        src[i + 1] = key;
    }

    public static void main(String[] args) {

        InsertSortRecursive t = new InsertSortRecursive();
//        int e[] = {8, 7, 2, 5, 9, 8, 5};
//        t.insertSortRecursive(e, e.length - 1);
//        System.out.println(Arrays.toString(e));
//
//        int a[] = {8, 7, 2, 5, 9, 8};
//        t.insertSortRecursive(a, a.length - 1);
//        System.out.println(Arrays.toString(a));
//
//        int b[] = {8, 7};
//        t.insertSortRecursive(b, b.length - 1);
//        System.out.println(Arrays.toString(b));
//
//        int c[] = {8};
//        t.insertSortRecursive(c, c.length - 1);
//        System.out.println(Arrays.toString(c));
//
//        int d[] = {};
//        t.insertSortRecursive(d, d.length - 1);
//        System.out.println(Arrays.toString(d));

        int f[] = {8, 7, 2, 898, 110, 99};
        t.insertSortRecursive(f, f.length - 1);
        System.out.println(Arrays.toString(f));
    }

}
