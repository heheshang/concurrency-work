package com.wangwenjun.concurrency.book16;

import java.util.Optional;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 5:30
 */
public class FlightSecurity {

    private int count = 0;

    private String boardingPass = "null";

    private String idCard = null;


    public synchronized void pass(String boardingPass, String idCard) {

        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();

    }

    private void check() {

        Optional.of(toString()).ifPresent(System.out::println);
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("===Exception====" + toString());
        }
    }


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("FlightSecurity{");
        sb.append("count=").append(count);
        sb.append(", boardingPass='").append(boardingPass).append('\'');
        sb.append(", idCard='").append(idCard).append('\'');
        sb.append('}');
        return sb.toString();
    }
}