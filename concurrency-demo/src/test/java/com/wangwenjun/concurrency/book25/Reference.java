package com.wangwenjun.concurrency.book25;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 2:44
 */
public class Reference {

    private final byte[] data = new byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {

        System.out.println("the reference will be GC.");
    }
}
