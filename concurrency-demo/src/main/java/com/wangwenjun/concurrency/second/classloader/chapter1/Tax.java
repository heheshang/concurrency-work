/*
package com.wangwenjun.concurrency.second.classloader.chapter1;

import javafx.util.Pair;

import java.math.BigDecimal;

*/
/**
 * 类描述
 *
 * @author zqf
 * @since 2019/1/4 15:33
 *//*

public class Tax {

    //个税起征点
    private static final BigDecimal beginPoint = BigDecimal.valueOf(5000);
    //北京2017年7月到2018年6月社保上限
    private static final BigDecimal sheBaoLimit = BigDecimal.valueOf(25401);

    public static void main (String[] args) {

        //薪水
        BigDecimal salary = new BigDecimal(args[0]);

        //其它专项扣除
        BigDecimal otherDeduct = new BigDecimal(args[1]);

        //社保专项空出
        BigDecimal sheBaoDeduct = BigDecimal.ZERO;

        //计算五险一金

        //社保基数
        BigDecimal sheBaoPay = salary;

        if (salary.compareTo(sheBaoLimit) > 0) {
            sheBaoPay = sheBaoLimit;
        }

        //养老保险计算
        sheBaoDeduct = sheBaoDeduct.add(sheBaoPay.multiply(BigDecimal.valueOf(0.08)));

        //医保保险计算
        sheBaoDeduct = sheBaoDeduct.add(sheBaoPay.multiply(BigDecimal.valueOf(0.02))).add(BigDecimal.valueOf(2));

        //失业保险计算

        //生育保险计算

        //工伤保险计算
        sheBaoDeduct = sheBaoDeduct.add(sheBaoPay.multiply(BigDecimal.valueOf(0.002)));

        //公积金
        sheBaoDeduct = sheBaoDeduct.add(sheBaoPay.multiply(BigDecimal.valueOf(0.12)));

        sheBaoDeduct = sheBaoDeduct.setScale(2, BigDecimal.ROUND_HALF_UP);
//        sheBaoDeduct = BigDecimal.valueOf(4500);

        //计算个税

        //个税循环扣除计算
        Tax.calculate(salary, sheBaoDeduct, otherDeduct, BigDecimal.ZERO, 1, BigDecimal.ZERO);

    }


    private static void calculate(BigDecimal salary, BigDecimal sheBaoDeduct, BigDecimal otherDeduct, BigDecimal accumulativeTax, int month, BigDecimal postTaxSalary) {

        if (month > 12) {
            System.out.println(String.format("12个月平均工资为%s元", postTaxSalary.divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP)));
            return;
        }

        BigDecimal taxSalary = (salary.subtract(sheBaoDeduct).subtract(beginPoint).subtract(otherDeduct)).multiply(BigDecimal.valueOf(month));
        BigDecimal tax = BigDecimal.ZERO;
        if (taxSalary.compareTo(BigDecimal.ZERO) >= 0) {
            Pair<BigDecimal, BigDecimal> taxRatePair = getTaxRate(taxSalary);

            tax = (taxSalary.multiply(taxRatePair.getKey()).subtract(taxRatePair.getValue()).subtract(accumulativeTax)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        accumulativeTax = accumulativeTax.add(tax);
        BigDecimal currentPostTaxSalary = salary.subtract(sheBaoDeduct).subtract(tax);
        postTaxSalary = postTaxSalary.add(currentPostTaxSalary);
        System.out.println(String.format("当前%s月份的税后工资为%s元, 社保%s元, 税费%s元", month, currentPostTaxSalary, sheBaoDeduct, tax));
        month++;
        calculate(salary, sheBaoDeduct, otherDeduct, accumulativeTax, month, postTaxSalary);
    }


    private static Pair<BigDecimal, BigDecimal> getTaxRate(BigDecimal tax) {

        if (tax.compareTo(BigDecimal.valueOf(36000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.03), BigDecimal.ZERO);
        } else if (tax.compareTo(BigDecimal.valueOf(36000)) > 0 && tax.compareTo(BigDecimal.valueOf(144000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.1), BigDecimal.valueOf(2520));
        } else if (tax.compareTo(BigDecimal.valueOf(144000)) > 0 && tax.compareTo(BigDecimal.valueOf(300000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.2), BigDecimal.valueOf(16920));
        } else if (tax.compareTo(BigDecimal.valueOf(300000)) > 0 && tax.compareTo(BigDecimal.valueOf(420000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.25), BigDecimal.valueOf(31920));
        } else if (tax.compareTo(BigDecimal.valueOf(420000)) > 0 && tax.compareTo(BigDecimal.valueOf(660000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.3), BigDecimal.valueOf(52920));
        } else if (tax.compareTo(BigDecimal.valueOf(660000)) > 0 && tax.compareTo(BigDecimal.valueOf(960000)) <= 0) {
            return new Pair<>(BigDecimal.valueOf(0.35), BigDecimal.valueOf(85920));
        } else {
            return new Pair<>(BigDecimal.valueOf(0.45), BigDecimal.valueOf(181920));
        }
    }
}
*/
