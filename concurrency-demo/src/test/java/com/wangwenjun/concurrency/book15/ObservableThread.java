package com.wangwenjun.concurrency.book15;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-01-16-下午 4:11
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    //指定task的实现，默认情况下使用EmptyLifeCycle
    public ObservableThread(Task<T> task) {

        this(new TaskLifeCycle.EmptyLifeCycle<>(), task);

    }

    //指定TaskLifeCycle的同时指定task
    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {

        super();
        // task 不允许为空
        if (task == null)
            throw new IllegalArgumentException("Task is required");

        this.lifeCycle = lifeCycle;

        this.task = task;
    }


    @Override
    public final void run() {

        this.update(Cycle.START, null, null);
        try {

            this.update(Cycle.RUNNING, null, null);

            T result = this.task.call();

            this.update(Cycle.DONE, result, null);

        } catch (Exception e) {

            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {

        this.cycle = cycle;

        if (lifeCycle == null) {
            return;
        }

        try {
            switch (cycle) {
                case START:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception e1) {

            if (cycle == Cycle.ERROR) {
                throw e1;
            }
        }

    }

    @Override
    public Cycle getCycle() {

        return this.cycle;
    }
}
