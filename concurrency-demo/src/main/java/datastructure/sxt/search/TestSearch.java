package datastructure.sxt.search;

/**
 * 在分数中查询分数的指定索引位置
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-29-上午 8:54
 */
public class TestSearch {

    public static void main(String[] args) {

        int[] scoreArr = {89, 45, 78, 45, 100, 98, 86, 100, 65};
        int score = 65;

        int index = search(scoreArr, score);

        if (index == -1) {
            System.out.println("the score not found");
        } else {
            System.out.println("the score  index is " + index);
        }
    }

    private static int search(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

}
