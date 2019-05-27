package com.wangwenjun.concurrency.book16

object Tableware {

}

class Tableware(final val toolName: String) {
	override def toString: String = {
		val sb = new StringBuilder("Tableware{")
		sb.append("toolName='").append(toolName).append('\'')
		sb.append('}')
		sb.toString
	}
}