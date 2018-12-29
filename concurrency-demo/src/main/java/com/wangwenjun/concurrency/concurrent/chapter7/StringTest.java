package com.wangwenjun.concurrency.concurrent.chapter7;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/22 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class StringTest {
    public static void main(String[] args) {
        String s = "Hello";
        /**
         *
         * public String replace(CharSequence target, CharSequence replacement) {
         *         return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
         *                 this).replaceAll(Matcher.quoteReplacement(replacement.toString()));
         *     }
         *
         * public static Pattern compile(String regex, int flags) {
         *         return new Pattern(regex, flags);
         *     }
         *
         *     返回一个新的string 对象
         */
        String s2 = s.replace("l", "k");


        System.out.println(s2.getClass() + " " + s2.hashCode());
        System.out.println(s.getClass() + " " + s.hashCode());
    }
}
