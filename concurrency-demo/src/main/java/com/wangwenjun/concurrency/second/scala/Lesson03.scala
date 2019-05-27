package com.wangwenjun.concurrency.second.scala

/**
  * Created by root on 2017/11/13.
  * 上一节课知识回顾：
  * 1、安装scala运行环境    scala安装包  配置环境变量  cmd  scala -version   scala
  * 2、安装scala开发环境   （1）在已有的eclipse中安装支持scala开发插件   （2）官网下载scala IDE
  * （3）IDEA 安装支持scala开发的插件
  * 3、如何创建一个变量和常量   var   val：一旦被赋值  不能被修改
  * 4、如何定义一个类
  * class Person{
  * 属性！！！  构造函数没有参数 无参构造函数
  * def this(name:String,age:Int){
  * //重一个构造函数  必须在构造函数中调用默认构造函数
  * this()
  * }
  * }
  * 创建一个有参数的构造函数怎么办？
  * class Student(name:String,age:String,score:Double){
  * }
  * 5、如何创建一个类的对象
  * （1）如果这个类的构造函数是无惨的
  * val p = new Person()
  * val p = new Person
  * (2)如果这个类的构造函数有参数
  * val s = new Student("Angelababy",29,100)
  * 6、object修饰的代码块中  定义的一切的属性都是静态，只需要通过类名就能调用
  * main肯定是在object修饰的代码块中定义
  * 7、if else   if elseif else
  * 8、for循环：
  * 单层for循环
  * 双层for循环
  * 带有判断语句的for循环
  * yied关键词   可以将符合条件的元素添加到一个集合中
  * val list = for(i <- 1 to 10;if i % 2 == 0) yield i
  * 小九九  九九乘法表
  * 9、while：先判断后执行循环体中的语句   do while：先执行循环体中的语句 后判断
  * 第n次求婚！！！
  * 10、函数：
  * 如何定义一个函数
  * def fun(name:String):Int = {
  * //函数体  如果需要返回值 那么可以使用return关键词
  * //当然return关键词就可以省略的，默认最后一行语句的执行结果就是这个函数的返回值！
  * }
  * （1）带有默认值的函数
  * def fun1(param1:String="Angelababy",param2:Int,param3:Double){}
  * 如何调用？
  * fun1("Dilireba",100,19.9)
  * fun1(100,99.99)
  * fun1(param3=99.99,param2=100)
  * (2)可变参数的函数
  * def fun2（scores:Any*）{}
  * fun2(10.0,12.0,13.2)
  * (3)嵌套函数：在函数中又定义了一个函数
  * （4）递归函数：在函数体中又调用了本身这个函数    求解了一个数字的阶乘
  * （5）匿名函数：没有名字的函数
  * (6)偏应用函数
  * （7）高阶函数：
  * 函数的参数或者返回是一个函数
  * def fun3(f:(Int,Int)=>Int):Int = {}
  * 简写原则：匿名函数中的参数在方法体只出现一次，那么可以使用_来代替
  * fun3(（_+_）)
  * （8）柯里化函数
  */
object Lesson03 {
	def main(args: Array[String]) {

		//柯里化函数 : 实质上就是高阶函数的简化版
		def fun1(a: Int)(b: Int) = {
			a + b
		}

		def fun2(param1: Int): (Int) => Int = {
			def fun3(param2: Int): Int = {
				param1 + param2
			}

			fun3
		}

		println(fun2(1)(2))
		println(fun1(1)(2))

		val str1 = "hello bjsxt"
		val str2 = "hello shsxt"
		val str3 = "HELLO bjsxt"
		println(str1.indexOf('o'))

		println(str1.equals(str3))


		//创建一个可变长度的字符串
		val strBuilder = new StringBuilder
		strBuilder.+('F')
		strBuilder.++=("I Love")
		strBuilder ++= "  Angelababy"
		strBuilder.append(" Very")
		strBuilder += ('P')
		println(strBuilder)


		//数组的讲解
		//创建了一个长度为10的数组   数组中元素的类型必须是Int
		val arr = new Array[Int](10)

		for (index <- 0 until arr.length) {
			arr(index) = index * index
		}

		arr.foreach((x: Int) => {
			println(x)
		})
		//简化版
		arr.foreach(println)


		/**
		  * 如何创建一个二维数组
		  * arr2是一个长度为3的数组，每一个元素又是一个数组
		  */
		val arr2 = new Array[Array[Int]](3)
		for (i <- 0 until arr2.length) {
			arr2(i) = new Array[Int](3)
		}
		//二维数组已经创建完毕
		//往二维数组中添加值
		for (i <- 0 until arr2.length; j <- 0 until arr2(i).length) {
			arr2(i)(j) = i * j
		}

		for (i <- 0 until arr2.length; j <- 0 until arr2(i).length) {
			print(arr2(i)(j) + "\t")
			println()
		}


		val arr3 = Array(1, 2, 3, 4)
		val arr4 = Array(2, 3, 4, 5)
		//concat合并数组
		val allArr = Array.concat(arr3, arr4)
		allArr.foreach(println)

		//fill方法会创建一个初始值为1的长度为5的数组
		val fillArr = Array.fill(5)(1)
		for (elem <- fillArr) {
			println(elem)
		}
		fillArr.foreach(println)


		//List集合
		val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
		list.foreach(println)

		//    filter:过滤元素
		def fl(x: Int): Boolean = {
			x > 3
		}

		println("=============================================================")
		var filtered = list.filter(fl)
		filtered.foreach(println)
		filtered = list.filter((x: Int) => x > 3)
		filtered.foreach(println)
		//    count:计算符合条件的元素个数
		val count = list.count((x: Int) => {
			x > 1
		})
		println(count)
		//    map：对元素操作
		val list1 = List("hello Angelababy", "hello dilireba", "hello zhangxinyi")
		//map函数传入的匿名函数的参数的类型一定是与集合中元素的类型一致
		//map函数传入的匿名函数的返回值的类型就是这个新集合的泛型
		val rest = list1.map((x: String) => {
			x.split(" ")
		})
		rest.foreach((x: Array[String]) => {
			x.foreach(println)
		})
		//    flatmap ：压扁扁平,先map再flat
		val rest1 = list1.flatMap((x: String) => {
			x.split(" ")
		})
		rest1.foreach(println)

		//set集合
		val set1 = Set(1, 2, 3, 4, 4)
		val set2 = Set(1, 2, 5)
		//交集   scala中字符有可能也是方法名
		val rest3 = set1.intersect(set2)
		val rest4 = set1.&(set2)
		rest4.foreach(println)

		println("===============取两个集合的差集===================")
		//取两个集合的差集   在set1集合中存在的但是set2集合中没有的元素
		set1.diff(set2)
		set1.&~(set2).foreach(println)

		val str = set1.mkString("~")
		println(str)

		set1.max
		set1.min

		set1.toList

		//=============================map========================================

		val map = Map(
			"1" -> "bjsxt",
			2 -> "shsxt",
			(3, "xasxt")
		)
		val keyIterator = map.keys.iterator
		while (keyIterator.hasNext) {
			val key = keyIterator.next()
			println(key + "====" + map.get(key).get)
		}

		println(map.get("1"))
		println(map.get("1000").getOrElse("no result"))

		map.foreach((x: (Any, String)) => {
			val key = x._1
			val value = x._2
			println("key:" + key + "\tvalue:" + value)
		})

		println(map.contains("1"))

		//============================Tuple============================
		val t2 = (1, 2)
		val t3 = Tuple3(1, 2, 3)
		val t4 = Tuple4("bjsxt", 1, true, 1.0)
		val t44 = ("bjsxt", 1, true, 1.0)
		println(t2._2)
		val iterator = t44.productIterator
		while (iterator.hasNext) {
			//在循环体内不要调用多次next方法
			println(iterator.next())
		}
		//二元组对象才有swap：调换位置
		val s2 = t2.swap
		println(s2._1)

		//============trait==============================
		/**
		  * trait可以类比成java中的接口  但是比java接口更高级一些
		  * trait中可以定义方法也可以实现方法
		  */
		val s = new Student
		s.read("Angelababy")
		s.listen("I Love You")
		s.sayName("Dilirema Love you")
	}

}

trait Read {
	def read(name: String) {
		println(name + " is reading")
	}

	def sayName(name: String): String
}

trait Listen {
	def listen(name: String) {
		println(name + " is listenning")
	}
}

class Student extends Read with Listen {
	override def sayName(name: String): String = {
		println("say you:" + name)
		name
	}
}