package com.wangwenjun.concurrency.book28.bus;

/**
 * <B>概要说明：</B>Bus 接口定义了EventBus的所有使用方法<BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 10:48
 */
public interface Bus {

    /**
     * 将某个对象注册到Bus上，从此之后该类就成为了Subscriber
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 将某个对象从Bus上取消注册，取消注册之后就不会再接收到来自Bus的任何消息
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交Event到默认的topic
     *
     * @param event
     */
    void post(Object event);

    /**
     * 提交Event到指定的topic
     *
     * @param topic
     * @param event
     */
    void post(Object event, String topic);

    /**
     * 关闭bus
     */
    void close();

    /**
     * 返回Bus的名称标识
     *
     * @return
     */
    String getBusName();

}
