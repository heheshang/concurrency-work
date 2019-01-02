package com.wangwenjun.concurrency.book29;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:17
 */
public interface DynamicRouter<E extends Message> {

    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    void dispatch(E message);


}
