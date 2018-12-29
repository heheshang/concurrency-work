package com.wangwenjun.concurrency.book27;

import com.wangwenjun.concurrency.book19.Future;
import com.wangwenjun.concurrency.book19.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:32
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Future<String> findOrderDetails(long orderId) {

        return FutureService.<Long, String>newService().submit(input ->
                {
                    try {
                        TimeUnit.SECONDS.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "The order details Information";
                }

                , orderId, null);
    }

    @Override
    public void order(String account, long orderId) {

        try {
            TimeUnit.SECONDS.sleep(10L);
            System.out.println("process the order for account " + account + " ,orderId " + orderId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
