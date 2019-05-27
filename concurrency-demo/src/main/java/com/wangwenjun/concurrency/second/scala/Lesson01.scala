package com.wangwenjun.concurrency.second.scala

/**
  * Created by root on 2017/11/10.
  *
  * 在scala里面没有static关键词的，那么我想定义一个静态的方法或者变量怎么办？
  *
  */
class Lesson01 {

}

//在 object修改的代码块中定义的方法或者变量或者常量都是静态的
object Lesson01 {
	def main(args: Array[String]) {
		//常量
		val name: String = "bjsxt"
		//变量
		var age = 10
		println(name + "\t" + age)

		val p = new Person("Angelababy", 99)
		p.sayName()
	}
}

//Person这个类默认的构造函数是空的
class Person(param1: String, param2: Int) {
	val name = param1
	var age = param2

	//unit代表了这个方法是没有返回值的
	def sayName(): Unit = {
		println("Angelababy" + "\t" + age)
	}


}
