package datastructure.sxt.linetable;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-23-上午 9:56
 */
public interface List {

    /**
     * 返回线性表的大小即元素的个数
     *
     * @return
     */
    public int size();

    /**
     * 返回序号为i的元素
     *
     * @param i
     * @return
     */
    public Object get(int i);

    /**
     * 如果线性表为空返回true,否则返回false
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 判断线性表中包含某个元素
     *
     * @param e
     * @return
     */
    public boolean contains(Object e);

    /**
     * 返回元素在线性表中的序号
     *
     * @param e
     * @return
     */
    public int indexOf(Object e);

    /**
     * 将数据元素e 插入到线性表i号位置
     *
     * @param i
     * @param e
     */
    public void add(int i, Object e);

    /**
     * 将数据元素e插入到线性表的尾部
     *
     * @param e
     */
    public void add(Object e);

    /**
     * 将元素e插入到元素o的位置之前
     *
     * @param o
     * @param e
     * @return
     */
    public boolean addBefore(Object o, Object e);

    /**
     * 将元素e插入到元素o的位置之后
     *
     * @param o
     * @param e
     * @return
     */
    public boolean addAfter(Object o, Object e);

    /**
     * 删除线性表中序号为i的元素 并返回
     *
     * @param i
     * @return
     */
    public Object remove(int i);

    /**
     * 替换线性表中序号为i的数据元素，并返回数据元素
     *
     * @param i
     * @param e
     * @return
     */
    public Object replace(int i, Object e);


}
