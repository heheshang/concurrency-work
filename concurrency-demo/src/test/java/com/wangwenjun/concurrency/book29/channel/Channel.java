package com.wangwenjun.concurrency.book29.channel;

import com.wangwenjun.concurrency.book29.message.Message;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:15
 */
public interface Channel<E extends Message> {


    void dispatch(E message);

}
