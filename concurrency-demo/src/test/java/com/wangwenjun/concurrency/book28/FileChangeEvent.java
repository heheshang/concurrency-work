package com.wangwenjun.concurrency.book28;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:51
 */
public class FileChangeEvent {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {

        this.kind = kind;
        this.path = path;
    }

    public Path getPath() {

        return path;
    }

    public WatchEvent.Kind<?> getKind() {

        return kind;
    }
}
