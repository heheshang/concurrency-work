package datastructure.sxt.linetable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-24-上午 11:24
 */
public class MyLinkedList<T> implements Iterable<T> {

    /**
     * 保存元素个数
     */
    private int size;

    /**
     * 记录修改的次数
     * 用处是：用于在遍历时，用于判断在迭代过程中是否发生了修改造作，如果发生了修改，则抛出异常
     */
    private int modCount = 0;

    /**
     * 定义两个结点 首节点和尾结点
     */
    private Node<T> begin;

    private Node<T> end;

    public MyLinkedList() {

        this.clear();
    }

    /**
     * 初始化一个空表
     */
    public void clear() {

        this.begin = new Node<T>(null, null, null);
        this.end = new Node<T>(null, this.begin, null);
        this.begin.next = this.end;
        this.size = 0;
        this.modCount++;
    }

    public boolean isEmpty() {

        return this.size == 0;
    }

    public boolean add(T value) {

        this.add(this.size(), value);
        return true;
    }

    public void add(int index, T value) {
        //在指定索引位置先找到那个索引处的节点类信息即先getNode(index).然后执行addBefore方法
        this.addBefore(this.getNode(index), value);
    }

    /**
     * 通过索引值返回对应位置的节点类信息
     *
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {

        Node<T> p;
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < this.size() / 2) {
            p = this.begin.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = this.end;
            for (int i = this.size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    /**
     * 在指定的那个节点P的前方插入值为value的一个元素
     *
     * @param p
     * @param value
     */
    public void addBefore(Node<T> p, T value) {
        // 定义新节点 前驱结点指向p 的前驱，后继结点指向p
        Node<T> newNode = new Node<>(value, p.prev, p);
        // 将新节点指向 P节点的后继
        newNode.prev.next = newNode;
        // 将新节点指向p 节点的前驱
        p.prev = newNode;
        this.size++;
        this.modCount++;
    }


    public int size() {

        return this.size;
    }

    /**
     * 返回指定索引处的元素值
     *
     * @param index
     * @return
     */
    public T get(int index) {

        return this.getNode(index).data;
    }

    public T set(int index, T value) {

        Node<T> p = this.getNode(index);
        T oldData = p.data;
        p.data = value;
        return oldData;
    }

    public T remove(int index) {

        return this.remove(this.getNode(index));
    }

    private T remove(Node<T> p) {
        //p的前驱指向 p 后继结点的前驱
        p.next.prev = p.prev;
        //p的后继指向p前驱结点的后继
        p.prev.next = p.next;
        this.size--;
        this.modCount++;
        return p.data;
    }

    @Override

    public Iterator<T> iterator() {

        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        //保留第一个起始位置的结点
        private Node<T> current = MyLinkedList.this.begin.next;

        //记录此刻集合修改的总次数，之后会拿modCount再和此值做比较，如果不相等，则抛出异常
        private int exceptedModCount = MyLinkedList.this.modCount;

        //判断是否可以向后移动
        private boolean canMove = false;

        @Override
        public boolean hasNext() {

            return this.current != MyLinkedList.this.end;
        }

        @Override
        public T next() {

            if (this.exceptedModCount != MyLinkedList.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T nextValue = this.current.data;
            this.current = this.current.next;
            this.canMove = true;
            return nextValue;
        }

        @Override
        public void remove() {

            if (this.exceptedModCount != MyLinkedList.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.canMove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(this.current.prev);
            this.canMove = false;
            this.exceptedModCount++;
        }
    }

    private static class Node<T> {

        //当前结点
        public T data;

        //到前一个结点的链
        public Node<T> prev;

        //到后一个结点的链
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {

            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
