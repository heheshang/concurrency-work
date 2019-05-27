package datastructure.sxt.linetable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-下午 1:21
 */
public class SingleLinkedList implements List {

    /**
     * 头结点不存储数据，为了编程方便
     */
    private Node head = new Node();

    /**
     * 一共有几个结点
     */
    private int size;

    @Override
    public int size() {

        return this.size;
    }

    @Override
    public Object get(int i) {
        //不能通过索引直接计算定位，而需要从头结点开始查找
        Node p = this.head;
        for (int j = 0; j < i; j++) {
            p = p.next;

        }
        return p.data;
    }

    @Override
    public boolean isEmpty() {

        return this.size == 0;
    }

    @Override
    public boolean contains(Object e) {

        Node p = this.head;
        for (int i = 0; i < this.size; i++) {
            p = p.next;
            return p.next.data.equals(e);
        }
        return false;
    }

    @Override
    public int indexOf(Object e) {

        Node p = this.head;
        for (int i = 0; i < this.size; i++) {
            p = p.next;
            if (p.data.equals(e)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void add(int i, Object e) {
        //如果i的位置错误报异常
        if (i < 0 || i > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组指针越界异常 " + i);
        }
        // 找到前一个结点，从head 结点开始找
        Node p = this.head;
        for (int j = 0; j < i; j++) {
            p = p.next;

        }
        //创建一个结点
        Node newNode = new Node();
        newNode.data = e;
        newNode.next = null;
        //指明新结点的直接后继
        newNode.next = p.next;
        //指明旧结点的后继结点
        p.next = newNode;
        this.size++;
    }

    @Override
    public void add(Object e) {

        this.add(this.size, e);
    }

    @Override
    public boolean addBefore(Object o, Object e) {

        this.add(this.indexOf(o), e);
        return true;
    }

    @Override
    public boolean addAfter(Object o, Object e) {

        if (this.indexOf(o) < this.size) {
            this.add(this.indexOf(o) + 1, e);
        } else {
            this.add(e);
        }

        return true;
    }

    @Override
    public Object remove(int index) {
        //如果i的位置错误报异常
        if (index < 0 || index > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组指针越界异常 " + index);
        }
        Node preNode = this.head;
        Node curNode = preNode.next;
        Object data = this.get(index);
        for (int j = 0; j < this.size; j++) {
            if ((curNode.data).equals(data)) {
                //待删除结点的前结点指向待删除结点的后结点
                preNode.next = curNode.next;
                break;
            }
            preNode = preNode.next;
            curNode = curNode.next;
        }
        this.size--;
        return data;
    }


    private boolean remove(Object e) {
        //先确定前驱结点和要删除的结点
        Node p = this.head;
        Node s = this.head.getNext();
        boolean flag = false;
        while (s != null) {
            if (e.equals(s.getData())) {
                flag = true;
                break;
            }
            // 如果没有找到，移动指向到后一个指针
            p = s;
            s = s.getNext();
        }
        // 如果找到就删除
        if (flag) {
            p.setNext(s.getNext());
            s.setNext(null);
            s = null;
        }
        return flag;
    }

    @Override
    public Object replace(int i, Object e) {
        //如果i的位置错误报异常
        if (i < 0 || i > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组指针越界异常 " + i);
        }
        Object data = this.get(i);
        Node p = this.head;
        for (int j = 0; j < this.size; j++) {
            p = p.next;
            if (data.equals(p.data)) {
                p.data = e;
                break;
            }
        }
        return this.get(i);
    }

    public String toString() {

        if (this.size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node p = this.head.next;
        for (int i = 0; i < this.size; i++) {

            if (i != this.size - 1) {
                builder.append(p.data).append(",");
            } else {
                builder.append(p.data);
            }
            p = p.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
