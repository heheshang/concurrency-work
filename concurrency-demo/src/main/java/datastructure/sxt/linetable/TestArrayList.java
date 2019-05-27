package datastructure.sxt.linetable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-上午 10:17
 */
public class TestArrayList {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(1);
        list.addBefore(1, 2);
        System.out.println(list.toString());
        list.addAfter(2, 3);
        list.addAfter(3, 4);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.add(3, 666);
        System.out.println(list.toString());
        list.replace(3, 777);
        list.addAfter(777, 888);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.toString());

    }
}
