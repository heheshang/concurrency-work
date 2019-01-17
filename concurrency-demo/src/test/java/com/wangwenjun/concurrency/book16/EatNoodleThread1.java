package com.wangwenjun.concurrency.book16;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 5:57
 */
public class EatNoodleThread1 extends Thread {

    private final String name;

    private final TablewarePair tablewarePair;

    public EatNoodleThread1(String name, TablewarePair tablewarePair) {

        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {

        while (true) {
            this.eat();
        }
    }

    private void eat() {

        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + " (left) ");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + " (right) ");
            System.out.println(name + " is eating now ... ");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + " (right) ");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + " (left) ");
        }

    }
}
