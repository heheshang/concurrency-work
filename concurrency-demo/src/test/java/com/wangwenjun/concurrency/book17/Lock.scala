package com.wangwenjun.concurrency.book17

/**
  * Created by ssk on 2019/1/17 0017.
  * desc:
  */
trait Lock {
  /**
    * 获取显示锁，没有获取锁的线程将被阻塞
    *
    * @throws java.lang.InterruptedException
    */
  @throws(classOf[InterruptedException])
  def lock()

  /**
    * 释放获取的锁
    */
  def unlock()
}
