package com.wangwenjun.concurrency.book28;


/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 上午 11:39
 */
public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);
}
