package com.wangwenjun.concurrency.book28.subscribe;

import java.lang.reflect.Method;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 11:34
 */
public class Subscriber {

    private final Object subscriberObject;

    private final Method subscriberMethod;

    private boolean disable = false;

    public Subscriber(Object subscriberObject, Method subscriberMethod) {

        this.subscriberMethod = subscriberMethod;
        this.subscriberObject = subscriberObject;
    }

    public Object getSubscriberObject() {

        return subscriberObject;
    }

    public Method getSubscriberMethod() {

        return subscriberMethod;
    }

    public boolean isDisable() {

        return disable;
    }

    public void setDisable(boolean disable) {

        this.disable = disable;
    }
}
