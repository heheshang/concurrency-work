package com.wangwenjun.concurrency.second.scala

/**
  * Created by root on 2017/11/15.
  * 回顾上节课的知识点：
  * 1、柯里化函数  高阶函数的简化版
  * def fun(name:String)(age:Int) = {
  *
  * }
  * def fun2(name:String):(Int)=>Int = {
  * def fun1(age:Int) = {
  * age
  * }
  * fun1
  * }
  * fun2("Angelababy")(29)
  * 2、数组  一维数组  二维数组
  * 数组取值？()
  * arr(0) = "abc"
  * arr(1) ="def"
  * 数组遍历  for(elem <- arr){}
  * 3、list  map set
  * filter
  * count
  * map
  * foreach
  * flatMap
  *
  * val list = List("Hello Angelababy","hello dilireba")
  * val mapList = list.map(x=>x.split)
  * val flatMapList = list.flatMap(x=>x.split)
  * 4、元组
  * 元组和list有什么区别呢？
  * 元组中可以存放不同类型的元素。而list集合中只能存放我们指定的泛型这个类型的元素
  * 如何定义一个元组  val t2 = (1,"bjsxt")
  * val ttwo = Tuple2(2,"shsxt")
  * 5、trait特质
  * java 接口做了类比
  * 区别？
  * 1、在trait中可以声明一个函数  也可以实现函数
  * 2、trait中既可以定义变量又可以定义常量
  *
  *
  * 模式匹配match
  *1.概念理解：
  * Scala 提供了强大的模式匹配机制，应用也非常广泛。
  * 一个模式匹配包含了一系列备选项，每个都开始于关键字 case。
  * 每个备选项都包含了一个模式及一到多个表达式。箭头符号 => 隔开了模式和表达式。
  *2.代码及注意点
  *
  */
object Lesson04 {
	def main(args: Array[String]): Unit = {
		for (i <- 1 to 10) {
			matchTest(i)
		}

	}


	/**
	  * 注意点：
	  * 1.模式匹配不仅可以匹配值，还可以匹配类型
	  * 2.模式匹配中，如果匹配到对应的类型或值，就不再继续往下匹配
	  * 3.模式匹配中，都匹配不上时，会匹配到 case _ ，相当于default
	  *
	  * @param x
	  */
	def matchTest(x: Int) = {
		x match {
			case 1 => println("one")
			case i: Int => println("Int")
			case 2 => println("two")
			case 3 => println("three")
			//当传入的参数无法匹配上面的内容的话，那么就会匹配上_
			case _ => println("default")
		}
	}
}
