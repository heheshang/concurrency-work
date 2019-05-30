package datastructure.sxt.search;

/**
 * 顺序结构  按照关键字排序
 * 二分查找
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-29-上午 8:54
 */
public class TestSearch2 {

    public static void main(String[] args) {
        //给定数组
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //给定要查找的值
        System.out.println(binarySearch(array, 1));
        //进行折半查找
        //输出结果

    }

    /**
     * 不使用递归
     * T(n)=O(logn)
     * S(n)=O(1)
     *
     * @param arr
     * @param findValue
     * @return
     */
    private static int binarySearch(int[] arr, int findValue) {

        if (arr == null) {
            return -1;
        }
        //起始位置
        int start = 0;
        //结束位置
        int end = arr.length - 1;
        int index = -1;
        while (start <= end) {
            int middle = (start + end) / 2;
            // 中值
            int middleValue = arr[middle];
            if (middleValue == findValue) {
                //如果查找的值等于中间值直接返回
                index = middle;
                break;
            } else if (middleValue > findValue) {
                end = middle - 1;
            } else {
                // (middleValue < findValue)
                start = middle + 1;
            }
        }
        return index;
    }

}
