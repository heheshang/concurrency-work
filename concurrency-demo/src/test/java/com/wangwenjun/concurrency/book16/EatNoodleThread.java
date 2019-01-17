package com.wangwenjun.concurrency.book16;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 5:57
 */
public class EatNoodleThread extends Thread {

    private final String name;

    private final Tableware leftTool;

    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {

        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {

        while (true){
            this.eat();
        }
    }

    private void eat() {
        synchronized (leftTool){
            System.out.println(name+" take up "+leftTool+" (left) ");

            synchronized (rightTool){
                System.out.println(name+" take up "+rightTool+" (right) ");
                System.out.println(name+" is eating now ... ");
                System.out.println(name+" put down "+rightTool+" (right) ");
            }

            System.out.println(name+" put down  "+leftTool+" (left) ");
        }
    }
}
