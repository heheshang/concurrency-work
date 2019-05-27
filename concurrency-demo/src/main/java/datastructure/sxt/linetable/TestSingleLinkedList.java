package datastructure.sxt.linetable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-下午 1:22
 */
public class TestSingleLinkedList {

    public static void main(String[] args) {

        List list = new SingleLinkedList();
        list.add(1);

        System.out.println(list.toString());
        list.addBefore(1, 2);
        System.out.println(list.toString());
        list.addAfter(2, 3);
        list.addAfter(3, 4);
        System.out.println(list.toString());

        list.add(3, 666);
        System.out.println(list.toString());
        list.replace(3, 777);
        System.out.println(list.toString());
        list.addAfter(777, 888);
        list.remove(2);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.toString());
    }
}
