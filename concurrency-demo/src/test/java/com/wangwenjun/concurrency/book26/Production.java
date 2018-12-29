package com.wangwenjun.concurrency.book26;

import java.util.Optional;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 下午 3:45
 */
public class Production extends InstructionBook {

    private final int prodId;

    public Production(int prodId) {

        this.prodId = prodId;
    }

    @Override
    protected void firstProcess() {

        Optional.of("execute the " + prodId+ " first process").ifPresent(System.out::println);
    }

    @Override
    protected void secondProcess() {
        Optional.of("execute the " + prodId+ " second process").ifPresent(System.out::println);
    }
}
