package com.wangwenjun.concurrency.book22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 12:47
 */
public class AutoSaveThread extends Thread {
    private Document document;

    public AutoSaveThread(Document document) {

        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {

        while (true) {
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1L);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
