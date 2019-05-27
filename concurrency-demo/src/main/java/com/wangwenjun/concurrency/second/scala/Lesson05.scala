package com.wangwenjun.concurrency.second.scala

/**
  * Created by root on 2017/11/15.
  * 样例类(case classes)
  */

case class Person10(var name: String, age: Int)

object Lesson05 {
	def main(args: Array[String]) {

		val p1 = Person10("dilireba", 29)
		val p2 = Person10("Angelababy", 30)
		val p3 = Person10("Angelababy", 31)

		val list = List(p1, p2, p3)
		list.foreach(x => {
			matchPerson(x)
		})
	}


	def matchPerson(p: Person10) = {
		p match {
			case Person10("dilireba", 29) => println(p.toString)
			case Person10("Angelababy", 30) => println(p.toString)
			case p: Person10 => println("=========" + p.toString)
			case _ => println("default")
		}
	}

}

