package com.wangwenjun.concurrency.book28.bus;


import com.wangwenjun.concurrency.book28.dispatcher.Dispatcher;
import com.wangwenjun.concurrency.book28.EventExceptionHandler;
import com.wangwenjun.concurrency.book28.register.Registry;

import java.util.concurrent.Executor;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 10:57
 */
public class EventBus implements Bus {

    /**
     * 用户维护subscribe的注册表
     */
    private final Registry registry = new Registry();

    /**
     * Event Bus 的名字
     */
    private String busName;

    /**
     * 默认的Event Bus 的名字
     */
    private static final String DEFAULT_BUS_NAME = "default";

    /**
     * 默认的topic的名字
     */
    private static final String DEFAULT_TOPIC = "default-topic";

    /**
     * 用于分发广播消息到各个subscribe的类
     */
    private final Dispatcher dispatcher;

    public EventBus() {

        this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName) {

        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public EventBus(String busName, EventExceptionHandler eventExceptionHandler, Executor executor) {

        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(eventExceptionHandler, executor);
    }

    public EventBus(EventExceptionHandler eventExceptionHandler) {

        this(DEFAULT_BUS_NAME, eventExceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }


    public EventBus(String busName, Dispatcher dispatcher) {

        this.busName = busName;
        this.dispatcher = dispatcher;
    }

    /**
     * 将注册subscriber 的动作直接委托给Register
     * @param subscriber
     */
    @Override
    public void register(Object subscriber) {

        this.registry.bind(subscriber);
    }

    /**
     * 解除注册subscriber 的动作直接委托给Register
     * @param subscriber
     */
    @Override
    public void unregister(Object subscriber) {

        this.registry.unbind(subscriber);
    }

    /**
     * 提交event 到默认的topic
     * @param event
     */
    @Override
    public void post(Object event) {

        this.post(event, DEFAULT_TOPIC);
    }

    /**
     * 提交event 到指定的topic，具体的动作是由dispatcher来完成的
     * @param event
     * @param topic
     */
    @Override
    public void post(Object event, String topic) {

        this.dispatcher.dispatch(this, registry, event, topic);
    }

    /**
     * 关闭销毁bus
     */
    @Override
    public void close() {

        this.dispatcher.close();
    }

    /**
     * 返回bus 名称
     * @return
     */
    @Override
    public String getBusName() {

        return this.busName;
    }
}
