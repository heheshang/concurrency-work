package com.wangwenjun.concurrency.book27;

import java.util.Map;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:59
 */
public class OrderMessage extends MethodMessage {

    public OrderMessage(Map<String, Object> params, OrderService orderService) {

        super(params, orderService);
    }

    @Override
    public void execute() {

        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");

        orderService.order(account, orderId);
    }
}
