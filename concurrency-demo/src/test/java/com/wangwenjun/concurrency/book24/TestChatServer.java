package com.wangwenjun.concurrency.book24;

import java.io.IOException;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 2:06
 */
public class TestChatServer {

    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }
}
