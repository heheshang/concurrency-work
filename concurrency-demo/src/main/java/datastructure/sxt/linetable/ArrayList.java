package datastructure.sxt.linetable;

import java.util.Arrays;

/**
 * java.util.arrayList 每次增长%50 在 grow 方法中
 * 顺序表 -->底层采用的是数组，但是长度可以动态的变化
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-上午 10:15
 */
public class ArrayList implements List {

    //底层是一个数组，目前没有确定长度
    private Object[] elementData;

    //不是数组分配了几个空间，而是元素的个数
    private int size;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * @param initialCapacity 数组的初始长度
     */
    public ArrayList(int initialCapacity) {
        //给数组分配指定的空间
        this.elementData = new Object[initialCapacity];
        //指定顺序表的元素个数默认是0
        //this.size = 0;
    }

    /**
     */
    public ArrayList() {
        //没有指定长度，长度是零
        this.elementData = new Object[4];
        //指定顺序表的元素个数默认是0
        // this.size = 0;
    }


    @Override
    public Object get(int i) {

        if (i < 0 || i > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组索引越界异常" + i);
        }
        return this.elementData[i];
    }

    @Override
    public boolean isEmpty() {

        return this.size == 0;
    }

    @Override
    public int size() {

        return this.size;
    }

    @Override
    public boolean contains(Object e) {

        for (int i = 0; i < this.size; i++) {
            return this.get(i).equals(e);
        }
        return false;
    }

    @Override
    public int indexOf(Object e) {

        for (int i = 0; i < this.size; i++) {
            if (this.get(i).equals(e)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void add(int i, Object e) {

        if (i < 0 || i > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组下标指针越界" + i);
        }

        //如果数组满了，扩容
        if (this.size == this.elementData.length) {

            this.grow();
        }
        //后移i及其i后面元素
        for (int j = this.size; j > i; j--) {
            //把前一个赋值给后一个
            this.elementData[j] = this.elementData[j - 1];
        }
        //给数组的第i个位置赋值
        this.elementData[i] = e;
        this.size++;
    }

    @Override
    public void add(Object e) {

        //扩容条件 数组满了，size=elementData.length
        //扩容策略：增长一倍，增长一半
        //如果数组满了，扩容
//        if (this.size == this.elementData.length) {
//
//            this.grow();
//        }
//
//        //给数组赋值，元素个数+1
//        this.elementData[this.size] = e;
//
//        this.size++;
//        System.out.println(this.elementData.length);
        this.add(this.size, e);

    }

    private void grow() {
//            //新创建一个新的数组 长度是就数组的2倍
//            Object[] newAddr = new Object[this.elementData.length * 2];
//            //将就数组数据拷贝的新数组
//            for (int i = 0; i < this.size; i++) {
//                newAddr[i] = this.elementData[i];
//                //elementData 指向新数组
//                this.elementData = newAddr;
//            }
        //扩容并复制
        this.elementData = Arrays.copyOf(this.elementData, this.elementData.length * 2);
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
    public Object remove(int i) {

        if (i < 0 || i > this.size) {
            throw new MyArrayIndexOutOfBoundsException("数组下标指针越界" + i);
        }
        Object el = this.elementData[i];
        for (int j = i; j < this.size - 1; j++) {
            this.elementData[j] = this.elementData[j + 1];
        }
        this.size--;
        return el;
    }

    @Override
    public Object replace(int i, Object e) {

        this.elementData[i] = e;
        return this.elementData[i];
    }

    public String toString() {

        if (this.size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            if (i != this.size - 1) {
                builder.append(this.elementData[i]).append(",");
            } else {
                builder.append(this.elementData[i]);
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
