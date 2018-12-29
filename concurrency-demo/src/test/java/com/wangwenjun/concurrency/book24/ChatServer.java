package com.wangwenjun.concurrency.book24;

import com.wangwenjun.concurrency.chapter13.SimpleThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 1:48
 */
public class ChatServer {

    private final int port;

    private SimpleThreadPool threadPool;

    private ServerSocket serverSocket;

    public ChatServer(int port) {

        this.port = port;
    }

    public ChatServer() {

        this(12323);
    }

    public void startServer() throws IOException {

        this.threadPool = new SimpleThreadPool(1, 2, 4, 1000, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        Optional.of(" chat server is started and listen at port :" + port).ifPresent(System.out::println);
        this.listen();

    }

    private void listen() throws IOException {

        for (; ; ) {
            Socket client = serverSocket.accept();
            this.threadPool.submit(new ClientHandler(client));

        }
    }


}
