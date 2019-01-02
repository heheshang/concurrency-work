package com.wangwenjun.concurrency.book27.active;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book27.future.ActiveFuture;

import java.lang.reflect.Method;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 9:32
 */
class ActiveMessage {

    private final Object[] objects;

    private final Method method;

    private final ActiveFuture<Object> future;

    private final Object service;

    private ActiveMessage(Builder builder) {

        this.future = builder.future;
        this.service = builder.service;
        this.method = builder.method;
        this.objects = builder.objects;
    }

    public void execute() {

        try {
            Object result = method.invoke(service, objects);

            if (future != null) {
                Future<?> realFuture = (Future<?>) result;

                Object realResult = realFuture.get();

                future.finish(realResult);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    static class Builder {

        private Object[] objects;

        private Method method;

        private ActiveFuture<Object> future;

        private Object service;

        public Builder useMethod(Method method) {

            this.method = method;
            return this;
        }

        public Builder returnFuture(ActiveFuture<Object> future) {

            this.future = future;
            return this;
        }

        public Builder withObjects(Object[] objects) {

            this.objects = objects;
            return this;

        }

        public Builder forService(Object service) {

            this.service = service;
            return this;
        }

        public ActiveMessage build() {

            return new ActiveMessage(this);
        }

    }
}
