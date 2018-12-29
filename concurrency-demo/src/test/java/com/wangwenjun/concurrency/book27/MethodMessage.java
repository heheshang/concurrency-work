package com.wangwenjun.concurrency.book27;

import java.util.Map;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:55
 */
public abstract class MethodMessage {

    protected final Map<String, Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {

        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
