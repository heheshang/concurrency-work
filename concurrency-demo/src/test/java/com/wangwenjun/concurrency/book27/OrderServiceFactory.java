package com.wangwenjun.concurrency.book27;

import static java.lang.Thread.currentThread;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 5:07
 */
public class OrderServiceFactory {

    private final static ActiveMessageQueue ACTIVE_MESSAGE_QUEUE = new ActiveMessageQueue();

    private OrderServiceFactory() {

    }

    public static OrderService toActiveObject(OrderService orderService) {

        return new OrderServiceProxy(orderService, ACTIVE_MESSAGE_QUEUE);
    }

    public static void main(String[] args) throws InterruptedException {

        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());

        orderService.order("hello", 1231323);
        System.out.println("Return immediately ");
        currentThread().join();
    }
}
