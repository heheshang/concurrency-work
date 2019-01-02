package com.wangwenjun.concurrency.book27.active;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book27.future.ActiveFuture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 9:49
 */
public class ActiveServiceFactory {

    private final static ActiveMessageQueue QUEUE = new ActiveMessageQueue();

    public static <T> T active(T instance) {

        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(), new ActiveInvocationHandler<>(instance)
        );

        return (T) proxy;
    }

    private static class ActiveInvocationHandler<T> implements InvocationHandler {

        private final T instance;

        ActiveInvocationHandler(T instance) {

            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);

                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture<Object>) result);
                }

                QUEUE.offer(builder.build());

                return result;
            } else {
                return method.invoke(instance, args);
            }
        }

        private void checkMethod(Method method) throws IllegalActiveMethod {

            if (!isReturnVoidType(method) && !isReturnFutureType(method)) {
                throw new IllegalActiveMethod("the method [" + method.getName() + "] return type must be Void/Future");
            }
        }

        private boolean isReturnFutureType(Method method) {

            return method.getReturnType().isAssignableFrom(Future.class);
        }

        private boolean isReturnVoidType(Method method) {

            return method.getReturnType().equals(Void.TYPE);
        }
    }
}
