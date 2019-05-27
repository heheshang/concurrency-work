package datastructure.sxt.linetable;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 借助栈实现进制转换
 * 将10进制转换成二进制
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-27-上午 9:33
 */
public class TestConversion {

    public static void main(String[] args) {
        // 给定一个10进制数
        int n = 13;
        // b被除数
        int t = n;
        Deque<Integer> deque = new LinkedList<>();

        while (t > 0) {
            //除以2得到余数作为二进制
            int mod = t % 2;
            System.out.println(mod + "--------->");
            deque.push(mod);
            //除以2得到商作为被除数继续
            int result = t / 2;
            t = result;
        }
        System.out.println(n + "-------------->");
        while (!deque.isEmpty()) {
            System.out.println(deque.pop());
        }
    }
}
