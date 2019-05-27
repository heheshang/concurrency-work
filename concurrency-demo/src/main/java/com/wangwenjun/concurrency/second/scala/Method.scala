package com.wangwenjun.concurrency.second.scala

/**
  * Created by ssk on 2019/3/21 0021.
  * desc:
  */
object Method {

	def main(args: Array[String]): Unit = {
		increase.apply(10)
		someNumbers.foreach((x: Int) ⇒ println(x))
		println("==========================")
		someNumbers.filter(x ⇒ x > 0).foreach(println)
		println("==========================")
		someNumbers.filter(_ > 0).foreach(println)

		println(a.apply(1, 2, 3))

		// 闭包
		var more = 1
		val addMore = (x: Int) ⇒ x + more

		def makeIncreaser(more: Int) = (x: Int) ⇒ x + more

		val incl = makeIncreaser(1)
		val incl9999 = makeIncreaser(9999)
		println(incl(10))
		println(incl9999(10))

		println("========重复参数=======")


		def echo(args: String*) = for (arg <- args) println(arg)

		echo("one")
		echo("one", "two", "three")
		println()

		val arr = Array("what's ", "up", "doc ?")
		// 这个标注告诉编译器把arr 的每个元素当做参数，而不是当做单一的参数传给echo
		echo(arr: _*)

		boom(3)
	}

	var increase = (x: Int) ⇒ {
		println("aa")
		println("bb")
		println("cc")
		x + 1
	}

	val someNumbers = List.apply(-11, -10, -5, 0, 5, 10)
	// 部分应用函数
	val a = sum _

	def sum(a: Int, b: Int, c: Int) = a + b + c


	def isGoodEnough(guess: Double): Boolean = ???

	def improve(guess: Any): Double = ???

	// 尾递归
	def approximateLoop(guess: Double): Double = {
		if (isGoodEnough(guess)) guess else approximateLoop(improve(guess))
	}

	def boom(x: Int): Int = if (x == 0) throw new Exception("boom") else boom(x - 1) + 1


}
