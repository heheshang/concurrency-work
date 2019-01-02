package com.wangwenjun.concurrency.book28;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:54
 */
public class FileChangeTest {

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        final EventBus eventBus = new AsyncEventBus(executor);

        eventBus.register(new FileChangeListener());

        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "D:\\person-work\\concurrency-work\\concurrency-demo\\monitor");

        monitor.startMonitor();
    }

}
