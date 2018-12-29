package com.wangwenjun.concurrency.book27;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book27.queue.ActiveMessageQueue;
import com.wangwenjun.concurrency.book27.service.OrderService;
import com.wangwenjun.concurrency.book27.service.OrderServiceImpl;
import com.wangwenjun.concurrency.book27.service.OrderServiceProxy;

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

        orderService.order("hello", 123);
        System.out.println("Return immediately ");
        Future<String> stringFuture = orderService.findOrderDetails(123);
        System.out.println(stringFuture.get());
        currentThread().join();

    }
}
