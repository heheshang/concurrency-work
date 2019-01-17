package com.wangwenjun.concurrency.book18;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-17-下午 12:35
 */
public final class IntegerAccumulator {


    private final int init;

    public IntegerAccumulator(int init) {

        this.init = init;
    }

    public IntegerAccumulator(IntegerAccumulator accumulator, int i) {

        this.init = accumulator.getValue() + i;
    }


    //对初始值增加1
    public IntegerAccumulator add(int i) {

//        this.init += i;
        return new IntegerAccumulator(this, i);
    }

    //返回当前的初始值
    public int getValue() {

        return this.init;
    }


    public static void main(String[] args) {

        IntegerAccumulator accumulator = new IntegerAccumulator(0);

        IntStream.range(0, 2).forEach(i -> new Thread(() -> {

            int inc = 0;
            while (true) {

//                int oldValue;
//                int result;
//                synchronized (IntegerAccumulator.class) {
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
//                }
                Optional.of(oldValue + "+" + inc + "=" + result).ifPresent(System.out::println);
                if (inc + oldValue != result) {
                    Optional.of("ERROR :" + oldValue + "+" + inc + "=" + result).ifPresent(System.err::println);
                }
                inc++;
                slowly();

            }
        }, "Thread-" + i).start());
    }

    private static void slowly() {

        try {
            TimeUnit.MILLISECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
