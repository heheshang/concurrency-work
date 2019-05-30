package datastructure.sxt.search;

/**
 * 顺序结构  按照关键字排序
 * 二分查找 T(n)=[LOG2]n
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-29-上午 8:54
 */
public class TestSearch3 {

    public static void main(String[] args) {
        //给定数组
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //给定要查找的值
        System.out.println(binarySearch(array, 10));
        //进行折半查找
        //输出结果

    }

    /**
     * 使用递归
     * T(n)=O(logn)
     * S(n)=O(1)*(logn)
     *
     * @param arr
     * @param findValue
     * @return
     */
    private static int binarySearch(int[] arr, int findValue) {

        if (arr == null) {
            return -1;
        }
        return binarySearch(arr, findValue, 0, arr.length - 1);
    }

    /**
     * 递归
     *
     * @param arr
     * @param findValue
     * @param low
     * @param high
     * @return
     */
    private static int binarySearch(int[] arr, int findValue, int low, int high) {

        //递归结束条件
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (findValue == arr[mid]) {
            return mid;
        } else if (findValue < arr[mid]) {
            return binarySearch(arr, findValue, low, mid - 1);
        } else {
            return binarySearch(arr, findValue, mid + 1, high);
        }
    }
}
