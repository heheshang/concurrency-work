package com.wangwenjun.concurrency.book27.active;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book27.service.OrderService;
import com.wangwenjun.concurrency.book27.service.OrderServiceImpl;

import static com.wangwenjun.concurrency.book27.active.ActiveServiceFactory.active;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 10:23
 */
public class ActiveTest {

    public static void main(String[] args) throws InterruptedException {

        OrderService orderService = active(new ActiveOrderServiceImpl());

        Future<String> future = orderService.findOrderDetails(12333);
        System.out.println(" i will be returned immediately");
        System.out.println(future.get());
    }

}
