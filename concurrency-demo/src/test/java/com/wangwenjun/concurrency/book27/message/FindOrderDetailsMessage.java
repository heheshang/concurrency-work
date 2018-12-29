package com.wangwenjun.concurrency.book27.message;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book27.future.ActiveFuture;
import com.wangwenjun.concurrency.book27.service.OrderService;

import java.util.Map;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:56
 */
public class FindOrderDetailsMessage extends MethodMessage {

    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {

        super(params, orderService);
    }

    @Override
    public void execute() {

        Future<String> realFuture = orderService.findOrderDetails((Long) params.get("orderId"));

        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");

        try {
            String result = realFuture.get();
            activeFuture.finish(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
            activeFuture.finish(null);
        }
    }
}
