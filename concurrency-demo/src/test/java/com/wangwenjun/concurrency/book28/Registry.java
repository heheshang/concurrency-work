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

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {

        List<Method> subscriberMethods = getSubscriberMethods(subscriber);

        subscriberMethods.forEach(method -> tierSubscriber(subscriber, method));
    }


    public void unbind(Object subscriber) {

        subscriberContainer.forEach((key, queue) -> {

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

        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());

        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscriberMethods(Object subscriber) {

        final List<Method> methods = new ArrayList<>();

        Class<?> temp = subscriber.getClass();

        while (temp != null) {
            Method[] declareMethods = temp.getDeclaredMethods();

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
