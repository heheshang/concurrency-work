package com.wangwenjun.concurrency.book29;

import java.util.HashMap;
import java.util.Map;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:19
 */
public class EventDispatcher implements DynamicRouter<Message> {

    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {

        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {

        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {

        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }
}
