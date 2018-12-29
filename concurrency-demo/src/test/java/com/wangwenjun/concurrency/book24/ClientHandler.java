package com.wangwenjun.concurrency.book24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 1:54
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private final String clientIdentity;

    public ClientHandler(final Socket socket) {

        this.socket = socket;
        this.clientIdentity = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }

    @Override
    public void run() {

        try {
            this.chat();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            this.release();
        }

    }

    private void release() {

        try {

            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {

        }
    }

    private void chat() throws IOException {

        BufferedReader bufferedReader = this.wrap2Reader(this.socket.getInputStream());

        PrintStream printStream = warp2print(this.socket.getOutputStream());

        String received;
        while ((received = bufferedReader.readLine()) != null) {
            System.out.printf("client:%s-message:%s\n", clientIdentity, received);

            if (received.equals("quit")) {
                write2Client(printStream, "client will close");
                socket.close();
                break;
            }
            write2Client(printStream, "Server: " + received);
        }
    }

    private BufferedReader wrap2Reader(InputStream inputStream) {

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private PrintStream warp2print(OutputStream outputStream) {

        return new PrintStream(outputStream);
    }

    private void write2Client(PrintStream print, String message) {

        print.println(message);
        print.flush();
    }

}
