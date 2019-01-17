package com.wangwenjun.concurrency.book29.router;

import com.wangwenjun.concurrency.book29.channel.Channel;
import com.wangwenjun.concurrency.book29.message.Message;

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
