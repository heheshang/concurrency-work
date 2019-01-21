package com.wangwenjun.concurrency.first.chapter2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class TaxCalculatorMain {

    public static void main(String[] args) {
        /*TaxCalaculator calculator = new TaxCalaculator(10000d, 2000d) {

            @Override
            public double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);*/

        // 非lambda 形式实现策略模式
        TaxCalaculator calculator1 = new TaxCalaculator(10000d, 2000d, new SimpleCalculatorStrategy());
        // lambda 形式实现策略模式 直接使用函数式编程
        TaxCalaculator calculator2 = new TaxCalaculator(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.15);
        System.out.println(calculator1.calculate());
        System.out.println(calculator2.calculate());
    }
}
