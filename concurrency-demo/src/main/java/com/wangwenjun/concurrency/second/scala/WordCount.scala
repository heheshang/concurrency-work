//package com.wangwenjun.concurrency.second.scala
//
//import org.apache.spark.SparkConf
//import org.apache.spark.SparkContext
//
///**
//  * 实现wordcount：
//  * 统计一个文本中每一个单词出现的次数
//  */
//object WordCount {
//	def main(args: Array[String]): Unit = {
//		/**
//		  * SparkConf对象是Spark的配置对象
//		  * 在这个对象中，
//		  * 1、可以设置Application所需要的资源（mem，core）情况
//		  * 2、设置Application的名称
//		  * 3、可以设置Spark运行模式    yarn mesos standalone local
//		  */
//		val conf = new SparkConf()
//		conf.setAppName("WordCount")
//		conf.setMaster("local")
//
//		/**
//		  * SparkContext对象是通往集群的通道
//		  */
//		val sc = new SparkContext(conf)
//		//    val rdd1 = sc.textFile("cs")
//		//    val rdd2 = rdd1.map((x:String) =>{
//		//      println("map log...")
//		//      x+"~"
//		//    })
//		//    val count = rdd2.count
//		//    println(count)
//
//		/**
//		  * lineRDD中每一行数据就是文本中每一条记录
//		  */
//		val lineRDD = sc.textFile("d:/wc")
//
//		/**
//		  * wordRDD 非kv格式的RDD  每一行数据就是一个单词
//		  */
//		val wordRDD = lineRDD.flatMap(line => line.split(" "))
//
//		/**
//		  * 将wordRDD 变成KV格式的RDD
//		  * pairRDD 每一行记录就是一个二元组类型的值（hello,1）
//		  */
//		val pairRDD = wordRDD.map(word => (word, 1))
//
//		/**
//		  * 按照key来分组，然后将每一组内的数据进行聚合
//		  * hello [1,1,1,1,1,1,1]
//		  *
//		  * 每一行数据
//		  * （hello，count）
//		  * （dilireba，count）
//		  */
//		val resultRDD = pairRDD.reduceByKey((v1: Int, v2: Int) => {
//			v1 + v2
//		})
//
//		resultRDD.foreach((x: (String, Int)) => {
//			println("word:" + x._1 + "\tcount：" + x._2)
//
//		})
//
//		/**
//		  * 释放资源
//		  */
//		sc.stop()
//	}
//}