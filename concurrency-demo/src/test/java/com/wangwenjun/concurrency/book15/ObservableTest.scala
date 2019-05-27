package com.wangwenjun.concurrency.book15


/**
  * @author ssk www.8win.com Inc.All rights reserved
  * @version v1.0
  * @date 2019-01-16-下午 4:32
  */
object ObservableTest {
	def main(args: Array[String]): Unit = {
		/*   Observable observableThread = new ObservableThread<>(() -> {
               try {
                   TimeUnit.SECONDS.sleep(10L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               System.out.println(" finished done");

               return null;
           });

           observableThread.start();*/
		val lifeCycle = new TaskLifeCycle.EmptyLifeCycle[String]() {
			override def onFinish(thread: Thread, result: String): Unit = {
				System.out.println("The result is " + result)
			}
		}
		//		val observableThread = new ObservableThread[String](lifeCycle, () => {
		//			def foo() = {
		//				try
		//					TimeUnit.SECONDS.sleep(10L)
		//				catch {
		//					case e: InterruptedException ⇒
		//						e.printStackTrace()
		//				}
		//				System.out.println(" finished done")
		//				" Hello  observer"
		//			}
		//
		//			foo()
		//		})
		//		observableThread.start()
	}
}
