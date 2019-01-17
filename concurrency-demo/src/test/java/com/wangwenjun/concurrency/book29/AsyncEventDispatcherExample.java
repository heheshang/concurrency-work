package com.wangwenjun.concurrency.book29;

import com.wangwenjun.concurrency.book29.channel.AsyncChannel;
import com.wangwenjun.concurrency.book29.router.AsyncEventDispatcher;
import com.wangwenjun.concurrency.book29.message.Event;

import java.util.concurrent.TimeUnit;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019年01月02日 下午 3:49
 */
public class AsyncEventDispatcherExample {

    static class AsyncInputEventHandler extends AsyncChannel {

        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventHandler(AsyncEventDispatcher dispatcher) {

            this.dispatcher = dispatcher;
        }

        @Override
        protected void handle(Event message) {

            EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;

            System.out.printf("X:%d,Y:%d", inputEvent.getX(), inputEvent.getY());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));

        }

        static class AsyncResultEventHandler extends AsyncChannel {


            @Override
            protected void handle(Event message) {

                EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("The result is ：" + resultEvent.getResult());
            }
        }

        public static void main(String[] args) {

            AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

            dispatcher.registerChannel(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));

            dispatcher.registerChannel(EventDispatcherExample.ResultEvent.class, new AsyncResultEventHandler());

            dispatcher.dispatch(new EventDispatcherExample.InputEvent(1, 2));


        }
    }

}
