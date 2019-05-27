package com.wangwenjun.concurrency.book15

import com.wangwenjun.concurrency.book15.Observable.Cycle

/**
  * @author ssk www.8win.com Inc.All rights reserved
  * @version v1.0
  * @date 2019-01-16-下午 4:11
  */
class ObservableThread[T](lifeCycle: TaskLifeCycle[T], task: Task[T]) //指定TaskLifeCycle的同时指定task
	extends Thread with Observable {
	// task 不允许为空
	if (task == null) throw new IllegalArgumentException("Task is required")
	private var cycle: Cycle = null

	//指定task的实现，默认情况下使用EmptyLifeCycle
	def this(task: Task[T]) {
		this(new TaskLifeCycle.EmptyLifeCycle[T], task)
	}

	override def run(): Unit = {
		//		this.update(Cycle.START, _, null)
		try {
			//			this.update(Cycle.RUNNING, _, null)
			val result = this.task.call
			//			this.update(Cycle.DONE, result, null)
		} catch {
			case e: Exception ⇒
			//				this.update(Cycle.ERROR, _, e)
		}
	}

	private def update(cycle: Observable.Cycle, result: T, e: Exception): Unit = {
		this.cycle = cycle
		if (lifeCycle == null) return
		try
			cycle match {
				case Cycle.START ⇒
					this.lifeCycle.onStart(Thread.currentThread)
				//          break //todo: break is not supported
				case Cycle.RUNNING ⇒
					this.lifeCycle.onRunning(Thread.currentThread)
				//          break //todo: break is not supported
				case Cycle.DONE ⇒
					this.lifeCycle.onFinish(Thread.currentThread, result)
				//          break //todo: break is not supported
				case Cycle.ERROR ⇒
					this.lifeCycle.onError(Thread.currentThread, e)
				//          break //todo: break is not supported
			}
		catch {
			case e1: Exception ⇒
				if (cycle eq Cycle.ERROR) throw e1
		}
	}

	override def getCycle: Observable.Cycle = this.cycle
}
