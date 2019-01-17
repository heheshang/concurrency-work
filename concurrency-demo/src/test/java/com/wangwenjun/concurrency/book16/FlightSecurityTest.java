package com.wangwenjun.concurrency.book16;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 5:33
 */
public class FlightSecurityTest {

    // 旅客线程
    static class Passengers extends Thread {

        private final FlightSecurity flightSecurity;

        private final String idCard;

        private final String boardingPass;


        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {

            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {

            while (true) {
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }

    public static void main(String[] args) {

        final FlightSecurity flightSecurity = new FlightSecurity();

        new Passengers(flightSecurity, "A123456", "AF123456").start();
        new Passengers(flightSecurity, "B123456", "BF123456").start();
        new Passengers(flightSecurity, "C123456", "CF123456").start();
        new Passengers(flightSecurity, "D123456", "DF123456").start();
    }

}
