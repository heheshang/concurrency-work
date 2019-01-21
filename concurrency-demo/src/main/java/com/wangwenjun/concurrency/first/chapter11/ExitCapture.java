package com.wangwenjun.concurrency.first.chapter11;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月25日 下午 5:29
 */
public class ExitCapture {

    public static void main(String[] args) {
        //使用 kill  而不是kill-9 在Linux 中停止 运行这个类的时候会进行资源释放操作
        // 在抛出异常的时候，导致jvm 退出也会调用notifyAndRelease 进行资源释放操作
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    System.out.println("ssss");
                    notifyAndRelease();
                }
        ));

        int i = 0;
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1L);
                Optional.of("i'm working").ifPresent(System.out::println);

            } catch (Exception e) {
//                e.printStackTrace();

            }

            i++;
            if (i > 10) {
                throw new RuntimeException();
            }
        }
    }

    private static void notifyAndRelease() {
        //
        System.out.println("处理释放资源 任务 例如 ：file socket connection");
    }
}
