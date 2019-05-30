package datastructure.sxt.sort;

import java.util.Arrays;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-30-上午 11:47
 */
public class TestQuickSort {


    private static void quickSort(int[] arr, int low, int high) {

        //todo 递归何时结束
        if (low < high) {
            //分区操作，将一个数组分成两个分区，返回分区界限的索引
            int index = partition(arr, low, high);
            //对左分区进行快速排序
            quickSort(arr, low, index - 1);
            //对右分区进行快速排序
            quickSort(arr, index + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        //指定左指针和右指针
        int i = low;
        int j = high;
        // 将第一个数作为基准值
        int x = arr[low];
        //使用循环实现分区操作
        while (i < j) {
            //1.从右向左移动j，找到第一个小于等于基准值的值arr[j]，当arr[j]>=x 时循环，j--
            while ((arr[j] >= x) && (i < j)) {
                j--;
            }
            //2.将右侧找到的小于基准数的值加入到左边的（坑）中，左指针向中间移动一个位置 i++
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            //3.从左向右移动i,找到第一个大于基准值的值arr[i],当arr[i]<x 时循环，i++
            while ((arr[i] < x) && (i < j)) {
                i++;
            }

            //4.将左侧找到的大于等于基准数的值加入到右边的（坑）中，右指针向中间移动一个位置 j--
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }

        //使用基准值填坑，这就是基准值的最后位置 此时i,j 是在同一个位置。将基准值填入arr[i]
        arr[i] = x;//arr[j]=x;

        //返回基准值的位置索引 此时i或者j 的值是相等的。返回i即可
        return i;
    }


    public static void quickSort(int[] arr) {

        int low = 0;
        int high = arr.length - 1;
        quickSort(arr, low, high);
    }


    public static void main(String[] args) {
        //给出无序数组
        int arr[] = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85, 99, 1, 22, 32, 4, 51, 0, 12, 13, 73};
        //输出无序数组
        System.out.println(Arrays.toString(arr));
        //快速排序

        // 进行一次操作
//        partition(arr, 0, arr.length - 1);

        quickSort(arr);
        //输出有序数据
        System.out.println(Arrays.toString(arr));
    }


}
