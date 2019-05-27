package com.wangwenjun.concurrency.book17

/**
  * Created by ssk on 2019/1/17 0017.
  * desc:
  */
class ReadWriteLockImpl(final var preferWriter: Boolean) extends ReadWriteLock {
	//创建对象锁
	private final val MUTEx: AnyRef = new AnyRef()
	//当前有多少线程写入
	private val writingWriters = 0;
	//当前有多少个线程正在等待写入
	private val waitingWriters = 0;
	//当前有多少个人正在read
	private val readingReaders = 0;

	def this() = {
		this(true)
	}

	/**
	  * 创建reader 锁
	  *
	  * @return
	  */
	override def readLock(): Lock = {
		new ReadLock(this)
	}

	/**
	  * 创建write 锁
	  *
	  * @return
	  */
	override def writeLock(): Lock = new WriteLock(this)

	override def getWritingWriters(): Int = this.writingWriters

	override def getWaitingWriters(): Int = this.waitingWriters

	override def getReadingReaders(): Int = this.readingReaders


	def incrementWritingWriters(): Int = this.writingWriters.+(1)

	def incrementWaitingWriters(): Int = this.waitingWriters.+(1)

	def incrementReadingWriters(): Int = this.readingReaders.+(1)


	def decrementWritingWriters(): Int = this.writingWriters.-(1)

	def decrementWaitingWriters(): Int = this.waitingWriters.-(1)

	def decrementReadingWriters(): Int = this.readingReaders.-(1)


	def getMutex(): AnyRef = this.MUTEx

	def getPreferWriter(): Boolean = this.preferWriter

	def changePrefer(preferWriter: Boolean) = {
		this.preferWriter = preferWriter
	}

}
