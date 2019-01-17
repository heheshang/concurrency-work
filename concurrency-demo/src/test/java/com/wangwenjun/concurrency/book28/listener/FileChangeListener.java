package com.wangwenjun.concurrency.book28.listener;

import com.wangwenjun.concurrency.book28.FileChangeEvent;
import com.wangwenjun.concurrency.book28.subscribe.Subscribe;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 2:53
 */
public class FileChangeListener {


    @Subscribe
    public void change(FileChangeEvent event) {

        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }

}
