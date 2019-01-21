package com.wangwenjun.concurrency.second.scala

import java.util.Date

/**
  * Created by root on 2017/11/10.
  * 在scala语法中，每一行语句后面不需要加分号，如果一行中有多条语句，语句与语句之间必须有分号
  * 注释和java是一模一样
  */
object Lesson02 {
  def main(args: Array[String]) {

    //    //if else
    //    val age = 20
    //    if(age < 18){
    //      println("禁止观看此电影")
    //    }else if(age >= 18 && age <= 20){
    //      println("需要有人陪伴才能观看")
    //    }else{
    //      println("尽情的看吧")
    //    }
    //    //for循环  打印1 。。。 10
    //
    //    for(i <-  1 to 10){
    //      println(i)
    //    }
    //    println(1 to 10)
    //    println(1 to 10)
    //    println(1 until 10)
    //
    //    println(1 .to(10,3))
    //    println(1 .until(10,3))
    //
    //    //双层for循环
    //    for(i <- 1 to 10 ; j <- 1 until 10){
    //      println("i =" + i +"j = " +  j)
    //    }
    //
    //    //带有判断语句的循环  统计出1到10内所有的偶数
    //    for(i <- 1 to 10 ; if i % 2 == 0 ; if i > 6){
    //      println(i)
    //    }
    //
    //    var num = 10 ;println("hello bjsxt")
    //
    //    //如何使用for循环打印一个小九九  <-：操作符
    //    for(i <- 1 to 9 ; j <- 1 until 10){
    //      if(i >= j){
    //        print( i +"* " + j + "=" + i * j + "\t")
    //      }
    //      if(i == j){
    //        println()
    //      }
    //    }
    //
    //    //for循环的yield关键词用法
    //    val list = for(i <- 1 to 10 ; if i % 2 == 0) yield i
    //    for(elem <- list){
    //      println(elem)
    //    }
    //
    //    //while、do while
    //    //在scala中没有++操作    如果想累加1   必须count += 1
    //    var index = 0
    //    while(index < 102){
    //      println(" 第" + index + "次求婚")
    //      index += 1
    //    }
    //
    //    index -= 1
    //    do{
    //      println(" 第" + index + "次求婚")
    //      index -= 1
    //    }while(index > 0)
    //
    //    //这是一个完整版的函数
    //    def fun1(a:Int,b:Int):Int = {
    //      return a+b
    //    }
    //
    //
    //    //这是一个简化版的函数   最后一行语句的执行结果就是这个函数的返回值
    //    def fun1_01(a:Int,b:Int) = {
    //      a+b
    //    }
    //
    //   println( fun1_01(1,2))
    //
    //
    //    //创建一个带有默认值参数的函数   函数中参数的默认修饰符是val
    //    def fun2(a:Int,b:Int=10,c:Int = 20)={
    //      a+b+c
    //    }
    //
    //    println(fun2(a=10,c=100))
    //    //创建一个可变参数长度的函数
    //    def fun3(elems:Int*)={
    //      for(elem <- elems){
    //        println(elem)
    //      }
    //    }
    //    fun3(1,2,3,4,5,6,7,8,9)
    //
    //    //递归函数    求解5 的阶乘   推测类型 需要有依据 找不根据推测不出类型的
    //    def fun4(num:Int):Int = {
    //      if(num == 1){
    //        num
    //      }else{
    //        num * fun4(num - 1)
    //      }
    //    }
    //    println(fun4(5))
    //
    //
    //    //匿名函数 ： 没有名字的函数
    //   val fun5 = (name:String,facePower:Double) => {
    //      println(name + "\tfacePower:" + facePower)
    //    }
    //     fun5("Angelababy",100)
    //
    //    //偏应用函数是一种表达式，不需要提供函数需要的所有参数，只需要提供部分，或不提供所需参数
    //    def log(date:Date,log:String) = {
    //      println(date + "\t" + log)
    //    }
    //
    //    //在调用log函数的时候  每次都需要传入一个date对象  烦
    //    val date = new Date()
    //    log(date,"log1")
    //    log(date,"log2")
    //    log(date,"log3")
    //
    //    val logWithDate = log(date,_:String)
    //    logWithDate("log1")
    //    logWithDate("log2")
    //    logWithDate("log3")

    /**
      * 什么是高阶函数？
      * 函数的参数是函数，或者函数的返回类型是函数，或者函数的参数和函数的返回类型是函数的函数
      * scala支持面向函数编程
      * scala支持面向对象变成
      *
      * fun6函数的参数要一个函数  要一个什么样的函数？
      */
    def fun6(f: (Int, Int) => Int): Int = {
      f(100, 200)
    }

    def f(a: Int, b: Int) = {
      a + b
    }

    println(fun6(f))

    //原则：匿名函数中参数在函数体中只出现一次可以用_代替
    println(fun6((a: Int, b: Int) => {
      a + b
    }))
    println(fun6((_ + _)))
  }
}
