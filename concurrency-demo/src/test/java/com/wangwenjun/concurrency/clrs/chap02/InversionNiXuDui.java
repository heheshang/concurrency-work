package com.wangwenjun.concurrency.clrs.chap02;

/**
 * 逆序对
 * 统计逆序对个数。如（2,3,8,6,1）有如下5个逆序对：2,1  3,1  8,1  6,1  8,6
 * * 则输出5.
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-03-20-下午 3:06
 */
public class InversionNiXuDui {

    int count = 0;

    public int calculate(int a[], int begin, int end) {

        if (begin == 0 && end == a.length - 1) {
            count = 0;
        }

        int mid = (begin + end) / 2;

        if (begin < mid) {
            calculate(a, begin, mid);
        }
        if (mid + 1 < end) {
            calculate(a, mid + 1, end);
        }

        count(a, begin, mid, end);
        if (begin == 0 && end == a.length - 1) {
            return count;
        }
        return 0;
    }

    /**
     * 思路：模仿merge sort中的merge过程，当两个数组merge时，如果先进入result数组的是右边的数组中的某个数字，
     * 那么左边数组中的所有剩下的数字都可以和右边数组的这个数字组成一个逆序对 -- 因为merge数组的时候，左右数组都是有序数组
     */
    private void count(int[] a, int begin, int mid, int end) {

        if (a.length == 0) {
            return;
        }

        int[] left = new int[mid - begin + 2];
        int[] right = new int[end - mid + 1];

        for (int i = 0; i < left.length - 1; i++) {
            left[i] = a[begin + i];
        }
        left[left.length - 1] = Integer.MAX_VALUE;

        for (int i = 0; i < right.length - 1; i++) {
            right[i] = a[mid + i + 1];
        }
        right[right.length - 1] = Integer.MAX_VALUE;

        int l = 0, r = 0;
        for (int i = begin; i <= end; i++) {
            if (right[r] < left[l]) {
                a[i] = right[r++];
                count += left.length - l - 1;
            } else {
                a[i] = left[l++];
            }
        }
    }

    public static void main(String[] args) {

        InversionNiXuDui t = new InversionNiXuDui();
        int e[] = {8, 7, 2, 5, 9, 8, 5};    //10
        System.out.println(t.calculate(e, 0, e.length - 1));

        int a[] = {8, 7, 2, 5, 9, 8};    //6
        System.out.println(t.calculate(a, 0, a.length - 1));

        int b[] = {8, 7};    //1
        System.out.println(t.calculate(b, 0, b.length - 1));

        int c[] = {8};    //0
        System.out.println(t.calculate(c, 0, c.length - 1));

        int d[] = {};    //0
        System.out.println(t.calculate(d, 0, d.length - 1));

        int f[] = {8, 7, 2, 10, 100, 99, 80, 98};    //8
        System.out.println(t.calculate(f, 0, f.length - 1));
    }
}
