package com.wangwenjun.concurrency.book28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 10:58
 */
class Registry {

    //存储subscriber 集合和topic 之间关系的map
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        //获取subscriber object 的方法集合然后进行绑定
        List<Method> subscriberMethods = getSubscriberMethods(subscriber);

        subscriberMethods.forEach(method -> tierSubscriber(subscriber, method));
    }


    public void unbind(Object subscriber) {

        subscriberContainer.forEach((key, queue) -> {
            // 为了提高速度，支队subscriber进行失效操作
            queue.forEach(s -> {
                if (s.getSubscriberObject() == subscriber) {
                    s.setDisable(true);
                }
            });
        });

    }

    private void tierSubscriber(Object subscriber, Method method) {

        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);

        String topic = subscribe.topic();
        //当某个topic 没有subscriber Queue的时候创建一个
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        //创建一个subscriber并且加入subscriber列表中
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscriberMethods(Object subscriber) {

        final List<Method> methods = new ArrayList<>();

        Class<?> temp = subscriber.getClass();
        // 不断获取当前类和父类的所有@Subscribe 方法
        while (temp != null) {
            //获取所有的方法
            Method[] declareMethods = temp.getDeclaredMethods();
            // 只有public 方法&& 有一个入参&& 最重要的是被@subscribe注解标记的方法才符合回调方法
            Arrays.stream(declareMethods)
                    .filter(
                            method -> method.isAnnotationPresent(Subscribe.class)
                                    && method.getParameterCount() == 1
                                    && method.getModifiers() == Modifier.PUBLIC
                    ).forEach(methods::add);

            temp = temp.getSuperclass();

        }

        return methods;
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(String topic) {

        return subscriberContainer.get(topic);
    }
}
