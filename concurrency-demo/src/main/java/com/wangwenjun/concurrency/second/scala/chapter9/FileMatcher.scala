package com.wangwenjun.concurrency.second.scala.chapter9

import java.io.{File, PrintWriter}
import java.util.Date

/**
  * Created by ssk on 2019/3/22 0022.
  * desc:
  */
object FileMatcher {
	private def filesHere = (new File("D:\\person-work\\concurrency-work\\concurrency-demo\\src\\main\\java\\com\\wangwenjun\\concurrency\\second\\scala\\")).listFiles()

	/*def fileSEnding(quer: String) = for (file <- filesHere; if file.getName.endsWith("query")) yield {
		file
	}

	def fileMatching(query: String, matcher: (String, String) ⇒ Boolean) = {
		for (file <- filesHere; if matcher.apply(file.getName, query)) yield {
			file
		}
	}

	def fileCon(query: String) = fileMatching(query, _.endsWith(_))

	def fileCon1(query: String) = fileMatching(query, (fileName: String, query: String) ⇒ fileName.endsWith(query))

	*/

	def fileMatching(matcher: String ⇒ Boolean) = {
		for (file <- filesHere; if matcher(file.getName)) yield {
			file
		}
	}

	def fileEnding(query: String) = fileMatching(_.endsWith(query))

	def fileContaining(query: String) = fileMatching(_.contains(query))

	def fileRegex(query: String) = fileMatching(_.matches(query))

	def main(args: Array[String]): Unit = {
		fileEnding("Test").foreach(f ⇒ println(f.getName))
		fileContaining("Less").foreach(f ⇒ println(f.getAbsolutePath, f.getName))

		withPrintWriter(new File("data.txt"), writer ⇒ writer.println(new Date()))

		val file = new File("data1.txt")
		withPrintWriter1(file) { writer ⇒ writer.println(new Date()) }
	}

	def withPrintWriter(file: File, op: PrintWriter ⇒ Unit): Unit = {
		val writer = new PrintWriter(file)
		try {
			op(writer)
		} finally {
			writer.close()
		}
	}

	def withPrintWriter1(file: File)(op1: PrintWriter ⇒ Unit): Unit = {
		val writer = new PrintWriter(file)
		try {
			op1.apply(writer)
		} finally {
			writer.close()
		}
	}

	var assertionsEnable = true

	def byNameAssert(predicate: ⇒ Boolean) = {
		if (assertionsEnable && !predicate) {
			throw new AssertionError()
		}
	}
}
