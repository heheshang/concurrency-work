package datastructure.sxt.linetable;

/**
 * 栈只有push pop peek 等操作
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-05-24-下午 1:49
 */
public interface Stack {

    //返回栈的大小
    public int getSize();

    //判断栈是否为空
    public boolean isEmpty();

    //数据元素e入栈
    public void push(Object e);

    //栈顶元素出栈
    public Object pop();

    //取栈顶元素
    public Object peek();
}
