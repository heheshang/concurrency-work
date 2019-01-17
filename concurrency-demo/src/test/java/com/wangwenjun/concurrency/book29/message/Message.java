package com.wangwenjun.concurrency.book29.message;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:15
 */
public interface Message {

    Class<? extends Message> getType();
}
