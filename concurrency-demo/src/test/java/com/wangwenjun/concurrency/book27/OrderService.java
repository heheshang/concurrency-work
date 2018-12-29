package com.wangwenjun.concurrency.book27;


import com.wangwenjun.concurrency.book19.Future;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 4:30
 */
public interface OrderService {

    Future<String> findOrderDetails(long orderId);

    void order(String account, long orderId);
}
