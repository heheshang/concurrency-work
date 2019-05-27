package com.wangwenjun.concurrency.second.scala.chapter10

/**
  * Created by ssk on 2019/3/22 0022.
  * desc:
  */
object Chapter10 {

}

abstract class Element {
	def demo(): Unit = {
		println("Element's implementation invoke")
	}

	def contents: Array[String]

	val height: Int = contents.length

	val width: Int = if (height == 0) 0 else contents(0).length()

	def above(that: Element): Element = new ArrayElement(this.contents ++ that.contents)

	def beside(that: Element): Element = /* {
		val contents = new Array[String](this.contents.length)
		for (i <- 0 until this.contents.length) {
			contents(i) = this.contents.apply(i) + that.contents.apply(i)
		}
		new ArrayElement(contents)
	}*/
		new ArrayElement(for ((line1, line2) <- this.contents zip that.contents) yield {
			line1 + line2
		})

	override def toString: String = contents mkString "\n"
}

class ArrayElement(conts: Array[String]) extends Element {

	override def demo(): Unit = {
		println("ArrayElement's implementation invok")
	}

	override def contents: Array[String] = conts
}

final class ArrayElement1(conts: Array[String]) extends Element {

	override def demo(): Unit = {
		println("ArrayElement1's implementation invok")
	}

	val contents: Array[String] = conts
}

class ArrayElement2(val contents: Array[String]) extends Element {
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
	override val height: Int = s.length
	override val width: Int = 1

	override def demo(): Unit = {
		println("LineElement's implementation invok")
	}
}

class UniformElement(ch: Char, override val height: Int,
                     override val width: Int) extends Element {
	private val line = ch.toString * width

	//	def contents = Array.make(height, line)
	override def contents: Array[String] = ???
}

class Cat {
	val dangerous = false
}

class Tiger(
	           override val dangerous: Boolean,
	           private var age: Int
           ) extends Cat {

}

class Tiger1(param1: Boolean, param2: Int) extends Cat {
	override val dangerous: Boolean = param1
	private var age = param2
}