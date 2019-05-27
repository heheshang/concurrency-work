package com.wangwenjun.concurrency.second.scala.chapter12

/**
  * Created by ssk on 2019/3/25 0025.
  * desc:
  */
abstract trait Philosophical {
	def philosophical(): Unit = {
		println("T counsume memory ,therefore I am")
	}

	def test()
}
