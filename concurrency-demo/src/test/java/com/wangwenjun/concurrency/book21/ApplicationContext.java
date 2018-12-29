package com.wangwenjun.concurrency.book21;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 10:53
 */
public final class ApplicationContext {

    private ApplicationConfiguration configuration;

    private RuntimeInfo runtimeInfo;

    private static class Holder {

        private static ApplicationContext instance = new ApplicationContext();


    }

    public static ApplicationContext getContext() {

        return Holder.instance;
    }

    public ApplicationConfiguration getConfiguration() {

        return this.configuration;
    }

    public void setConfiguration(ApplicationConfiguration configuration) {

        this.configuration = configuration;
    }

    public RuntimeInfo getRuntimeInfo() {

        return this.runtimeInfo;
    }

    public void setRuntimeInfo(RuntimeInfo runtimeInfo) {

        this.runtimeInfo = runtimeInfo;
    }
}
