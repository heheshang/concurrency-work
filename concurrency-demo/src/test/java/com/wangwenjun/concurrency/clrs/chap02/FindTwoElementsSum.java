package com.wangwenjun.concurrency.clrs.chap02;

import java.util.Arrays;

/**
 * O(nlgn)的算法，给定n个整数的集合和一个整数x，算出来集合中是否有两个其和刚好为x的元素。
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-03-13-上午 11:46
 */
public class FindTwoElementsSum {

    private MergeSort t = new MergeSort();

    private BinarySearch b = new BinarySearch();

    public void find(int a[], int x) {

        boolean found = false;
        System.out.println("Search x= " + x + " in " + Arrays.toString(a));

        t.mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length - 1; i++) {

            if (b.bSearchRecursive(a, x - a[i], i, a.length - 1)) {
                System.out.println("	Found " + a[i] + ", " + (x - a[i]));
                found = true;
            }
        }
        if (!found) {
            System.out.println(" not found");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a1 = {2, 4, 3, 2, -1, 0, 9, 7};
        int[] a2 = {2, 1};
        int[] a3 = {1};
        int[] a4 = {};

        int x = 3;
        FindTwoElementsSum p = new FindTwoElementsSum();
        p.find(a1, x);
        p.find(a2, x);
        p.find(a3, x);
        p.find(a4, x);
    }
}
