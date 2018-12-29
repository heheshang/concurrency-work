package com.wangwenjun.concurrency.book19;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 9:46
 */
@FunctionalInterface
public interface Task<IN, OUt> {

    OUt get(IN input);
}
