package com.wangwenjun.concurrency.second.scala.chapter14

import junit.framework.TestCase
import org.junit.Assert.{assertEquals, fail}

/**
  * Created by ssk on 2019/3/26 0026.
  * desc:
  */
class ElementTestCase extends TestCase {

	def testUniformElement(): Unit = {
		assertEquals("sss", "ssss")
		try {
			fail()
		} catch {
			case e: IllegalArgumentException ⇒ "预期的"
		}
	}
}
