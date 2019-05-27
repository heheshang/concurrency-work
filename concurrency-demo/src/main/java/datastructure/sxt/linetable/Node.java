package datastructure.sxt.linetable;

/**
 * 单链表的结点
 * 特点：只能找后继 ，找前驱得遍历
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-下午 1:09
 */
public class Node {

    /**
     * 单链表的数据元素（数据域）
     */
    Object data;

    Node next;


    public Node() {

    }

    public Node(Object data) {

        super();
        this.data = data;
    }

    public Node(Object data, Node next) {

        this.data = data;
        this.next = next;
    }

    public Object getData() {

        return this.data;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public Node getNext() {

        return this.next;
    }

    public void setNext(Node next) {

        this.next = next;
    }
}
