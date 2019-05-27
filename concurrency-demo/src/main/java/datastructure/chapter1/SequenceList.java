package datastructure.chapter1;

import java.util.Arrays;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-14-下午 2:23
 */
public class SequenceList<T> {

    private final int DEFAULT_SIZE = 16;

    /**
     * 保存数组长度
     */
    private int capacity;

    /**
     * 定义一个数组，用来保存数据线性表
     */
    private Object[] elementData;

    /**
     * 用来存放数据线性表中当前元素的个数
     */
    private int size;

    /**
     * 以默认长度创建空的线性表
     */
    public SequenceList() {

        this.capacity = this.DEFAULT_SIZE;
        this.elementData = new Object[this.capacity];
    }

    public SequenceList(int initSize) {

        this.size = initSize;

        this.capacity = 1;
        while (this.capacity < initSize) {
            //通过循环将capacity设置为大于initSize的最小值
            this.capacity = this.capacity << 1;
        }
        this.elementData = new Object[this.capacity];
        this.size++;
    }

    /**
     * 获取线性表的大小
     *
     * @return
     */
    public int getSize() {

        return this.size;
    }

    /**
     * 获取索引i 处的元素
     *
     * @param i
     * @return
     */
    public T getElement(int i) {

        if (i < 0 || i > this.size - 1) {
            throw new IndexOutOfBoundsException("输入索引超过了线性的范围");
        }
        return (T) this.elementData[i];
    }

    /**
     * 根据元素查找在线性表中的位置，未查找到返回-1
     *
     * @param element
     * @return
     */
    public int getIndex(T element) {

        for (int i = 0; i < this.size; i++) {
            if (this.elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 保证数组容量大小
     *
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {

        if (minCapacity > this.capacity) {
            //不断将capacity*2 直到capacity 最小且大于minCapacity
            while (this.capacity < minCapacity) {

                this.capacity <<= 1;
            }
        }
        this.elementData = Arrays.copyOf(this.elementData, this.capacity);
    }

    /**
     * 向顺序线性表中指定位置插入数据
     *
     * @param element
     * @param index
     */
    public void insert(T element, int index) {

        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("插入元素位置超过线性表范围");
        }
        this.ensureCapacity(this.size + 1);
        //将索引处之后的元素向后移动
        System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
        this.elementData[index] = element;
        this.size++;
    }

    /**
     * 删除指定位置元素
     *
     * @param index
     * @return
     */
    public T delete(int index) {

        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("删除位置不在线性表索引范围内");
        }
        T old = (T) this.elementData[index];
        int numMove = this.size - index - 1;
        if (numMove > 0) {
            System.arraycopy(this.elementData, index + 1, this.elementData, index, numMove);
        }
        this.elementData[--this.size] = null;
        return old;
    }

    /**
     * 向线性表中添加元素
     *
     * @param element
     */
    public void add(T element) {

        this.insert(element, this.size);
    }

    /**
     * 向线性表中删除一个元素
     */
    public T remove() {

        return this.delete(this.size - 1);
    }

    /**
     * 检查线性表是否为空
     *
     * @return
     */
    public boolean isEmpty() {

        return this.size == 0;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        //将所有元素置为空
        Arrays.fill(this.elementData, null);
        this.size = 0;
    }

    public String toString() {

        if (this.isEmpty())
            return "[]";
        else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < this.size; i++) {
                sb.append(this.elementData[i].toString() + " ");
            }
            int len = sb.length();
            return sb.delete(len - 1, len).append("]").toString();
        }
    }

    public static void main(String[] args) {

        SequenceList<String> slist = new SequenceList<String>();
        slist.add("haha");
        slist.add("hehe");
        slist.add("xixi");
        System.out.println("添加元素后的顺序线性表为： " + slist);
        slist.insert("heihei", 2);
        System.out.println("在线性表的位置2插入元素： " + slist);
        slist.delete(2);
        System.out.println("删除线性表中位置2的元素： " + slist);
        slist.remove();
        System.out.println("删除线性表中的一个元素： " + slist);
        slist.getElement(1);
        System.out.println("获得线性表位置1处的元素" + slist);
        System.out.println("获取元素hehe所在位置： " + slist.getIndex("hehe"));
        //清空线性表
        slist.clear();
        System.out.println("清空线性表");
        System.out.println("清空后线性表是否为空： " + slist.isEmpty());
    }

}
