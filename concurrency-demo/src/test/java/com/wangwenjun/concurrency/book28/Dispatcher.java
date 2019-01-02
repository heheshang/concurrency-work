package com.wangwenjun.concurrency.book28;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 11:02
 */
public class Dispatcher {

    private final Executor executorService;

    private final EventExceptionHandler exceptionHandler;

    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_EXECUTOR_SERVICE = PreThreadExecutorService.Instance;

    public Dispatcher(Executor executorService, EventExceptionHandler exceptionHandler) {

        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Bus bus, Registry registry, Object event, String topic) {

        ConcurrentLinkedQueue<Subscriber> subscribers = registry.scanSubscriber(topic);

        if (null == subscribers) {
            if (exceptionHandler != null) {
                exceptionHandler.handle(new IllegalArgumentException("the topic " + topic + "not bind yet"), new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }

        subscribers.stream()
                .filter(subscriber -> !subscriber.isDisable())
                .filter(subscriber -> {
                    Method subscriberMethod = subscriber.getSubscriberMethod();

                    Class<?> cls = subscriberMethod.getParameterTypes()[0];
                    return cls.isAssignableFrom(event.getClass());

                }).forEach(subscriber -> realInvokeSubscribe(subscriber, event, bus));
    }

    private void realInvokeSubscribe(Subscriber subscriber, Object event, Bus bus) {

        Method subscriberMethod = subscriber.getSubscriberMethod();
        Object subscriberObject = subscriber.getSubscriberObject();
        executorService.execute(() -> {
            try {
                subscriberMethod.invoke(subscriberObject, event);
            } catch (Exception e) {

                if (null != exceptionHandler) {
                    exceptionHandler.handle(e, new BaseEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    public void close() {

        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    static Dispatcher newDispatcher(EventExceptionHandler eventExceptionHandler, Executor executor) {

        return new Dispatcher(executor, eventExceptionHandler);
    }

    static Dispatcher seqDispatcher(EventExceptionHandler eventExceptionHandler) {

        return new Dispatcher(SEQ_EXECUTOR_SERVICE, eventExceptionHandler);
    }

    static Dispatcher preThreadDispatcher(EventExceptionHandler eventExceptionHandler) {

        return new Dispatcher(PRE_EXECUTOR_SERVICE, eventExceptionHandler);
    }

    private static class SeqExecutorService implements Executor {

        private final static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {

            command.run();
        }

    }

    private static class PreThreadExecutorService implements Executor {

        private final static PreThreadExecutorService Instance = new PreThreadExecutorService();

        @Override
        public void execute(Runnable command) {

            new Thread(command).start();
        }
    }

    //默认EventContext 实现

    private static class BaseEventContext implements EventContext {

        private final String eventBusName;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String eventBusName, Subscriber subscriber, Object event) {

            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }


        @Override
        public String getSource() {

            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {

            return subscriber != null ? subscriber.getSubscriberObject() : null;
        }

        @Override
        public Method getSubscribe() {

            return subscriber != null ? subscriber.getSubscriberMethod() : null;
        }

        @Override
        public Object getEvent() {

            return this.event;
        }
    }
}
