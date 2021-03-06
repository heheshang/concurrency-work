package com.wangwenjun.concurrency.book28;

import com.wangwenjun.concurrency.book28.bus.EventBus;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:25
 */
public class DirectoryTargetMonitor {

    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath) {

        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath, final String morePaths) {

        this.eventBus = eventBus;

        this.path = Paths.get(targetPath, morePaths);
    }

    public void startMonitor() throws IOException {

        this.watchService = FileSystems.getDefault().newWatchService();

        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE
        );

        System.out.printf("The directory [%s] is monitoring ..\n", path);

        this.start = true;

        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();

                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    eventBus.post(new FileChangeEvent(child, kind));
                });

            } catch (Exception e) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    public void stopMonitor() throws IOException {

        System.out.printf("The directory [%s] monitor will be stop ... \n",path);

        Thread.currentThread().interrupt();

        this.start=false;
        this.watchService.close();

        System.out.printf("The directory [%s] monitor will be stop done ... \n",path);
    }
}
