package com.wangwenjun.concurrency.book16

/**
  * Created by ssk on 2019/1/16 0016.
  * desc:
  */
object TablewarePairTest {
	def main(args: Array[String]): Unit = {
		val fork = new Tableware("fork")
		val knife = new Tableware("knife")

		val tablewarePair = new TablewarePair(fork, knife);
		new EatNoodleThread1("A", tablewarePair).start()
		new EatNoodleThread1("B", tablewarePair).start()
		new EatNoodleThread1("C", tablewarePair).start()
		new EatNoodleThread1("D", tablewarePair).start()
		new EatNoodleThread1("E", tablewarePair).start()
		new EatNoodleThread1("F", tablewarePair).start()
	}
}
