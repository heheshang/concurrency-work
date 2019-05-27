package com.wangwenjun.concurrency.future;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2019-02-18-下午 4:27
 */
public class FutureExample6 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * 创建完整的 CompletableFuture
     * 以上代码一般来说被用于启动异步计算，getNow(null)返回计算结果或者 null
     */
    @Test
    public void completedFutureExample() {

        CompletableFuture<String> f = CompletableFuture.completedFuture("message");

        Assert.assertTrue(f.isDone());
        assertEquals("message", f.getNow(null));
    }

    /**
     * 运行简单的异步场景
     * 以上代码的关键点有两点：
     * <p>
     * CompletableFuture 是异步执行方式；
     * 使用 ForkJoinPool 实现异步执行，这种方式使用了 daemon 线程执行 Runnable 任务。
     */
    @Test
    public void thenApplyExample() {

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            Assert.assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        Assert.assertFalse(cf.isDone());
        sleepEnough();
        Assert.assertTrue(cf.isDone());
    }

    /**
     * 同步执行动作示例
     * 以上代码在异步计算正常完成的前提下将执行动作（此处为转换成大写字母）。
     */
    @Test
    public void thenApplyExample1() {

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {

            Assert.assertFalse(Thread.currentThread().isDaemon());

            return s.toUpperCase();

        });

        assertEquals("MESSAGE", cf.getNow(null));
    }

    /**
     * 异步执行动作示例
     * 相较前一个示例的同步方式，以下代码实现了异步方式，仅仅是在上面的代码里的多个方法增加"Async"这样的关键字
     */
    @Test
    public void thenApplyAsyncExample() {

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();

        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    /**
     * 使用固定的线程池完成异步执行动作示例
     * 我们可以通过使用线程池方式来管理异步动作申请，以下代码基于固定的线程池，也是做一个大写字母转换动作，代码如
     */
    @Test
    public void thenApplyAsyncWithExecutorExample() {

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
                    assertFalse(Thread.currentThread().isDaemon());
                    randomSleep();
                    return s.toUpperCase();
                }, executor);
    }

    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable r) {

            return new Thread(r, "custom-executor-" + count++);
        }
    });

    /**
     * 作为消费者消费计算结果示例
     * 假设我们本次计算只需要前一次的计算结果，而不需要返回本次计算结果，
     * 那就有点类似于生产者（前一次计算）-消费者（本次计算）模式了，示例代码如清单 10 所示。
     * <p>
     * <p>
     * 消费者是同步执行的，所以不需要在 CompletableFuture 里对结果进行合并。
     */
    @Test
    public void thenAcceptExample() {

        StringBuilder result = new StringBuilder();

        CompletableFuture.completedFuture("thenAccept message").thenAccept(result::append);

        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 异步消费示例
     * 相较于前一个示例的同步方式，我们也对应有异步方式，代码如清单 11 所示
     */
    @Test
    public void thenAcceptAsyncExample() {

        StringBuilder result = new StringBuilder();

        CompletableFuture<Void> future = CompletableFuture.completedFuture("thenAccept message").thenAcceptAsync(result::append);

        future.join();

        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 计算过程中的异常示例
     * 接下来介绍异步操作过程中的异常情况处理。
     * 下面这个示例中我们会在字符转换异步请求中刻意延迟 1 秒钟，然后才会提交到 ForkJoinPool 里面去执行
     * <p>
     * 首先，我们新建了一个已经完成并带有返回值message的CompletableFuture对象。然后我们调用thenApplyAsync方法，该方法会返回一个新的CompletableFuture。这个方法用异步的方式执行大写操作。这里还展示了如何使用delayedExecutor(timeout, timeUnit)方法来延时异步操作。
     * 然后我们创建了一个handler stage，exceptionHandler，这个阶段会处理一切异常并返回另一个消息message upon cancel。
     * 最后，我们显式的完成第二个阶段并抛出异常，它会导致进行大写操作的阶段抛出CompletionException。它还会触发handler阶段。
     * API补充：
     * <U> CompletableFuture<U> handle(BiFunction<? super T,Throwable,? extends U> fn)
     * 返回一个新的CompletionStage，无论之前的Stage是否正常运行完毕。传入的参数包括上一个阶段的结果和抛出异常。
     */
    @Test
    public void completeExceptionallyExample() {

      /*  CompletableFuture<Void> cf = CompletableFuture.completedFuture("message")
                .thenAcceptAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> {
            return (th != null) ? "message upon cancel" : "";
        });

        cf.completeExceptionally(new RuntimeException("completed exceptionally"));

        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());

        try {

            cf.join();

            Assert.fail("Should have thrown an exception");

        } catch (CompletionException ex) { // just for testing

            assertEquals("completed exceptionally", ex.getCause().getMessage());

        }

        assertEquals("message upon cancel", exceptionHandler.join());*/
    }

    /**
     * 9.取消计算
     * 和计算时异常处理很相似，我们可以通过Future接口中的cancel(boolean mayInterruptIfRunning)来取消计算。
     * API补充
     * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
     * 返回一个新的CompletableFuture，如果出现异常，则为该方法中执行的结果，否则就是正常执行的结果。
     */
    @Test
    public void cancelExample() {

    /*    CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        CompletableFuture cf2 = cf.exceptionally((Function) th -> "canceled message");

        assertTrue("Was not canceled ", cf.cancel(true));
        assertTrue("Was not completed exceptionally  ", cf.isCompletedExceptionally());

        assertEquals("canceled message", cf2.join());
*/
    }


    /**
     * 10.将Function作用于两个已完成Stage的结果之一
     * 下面的例子创建了一个CompletableFuture对象并将Function作用于已完成的两个Stage中的任意一个（没有保证哪一个将会传递给Function）。
     * 这两个阶段分别如下：一个将字符串大写，另一个小写。
     * public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T,U> fn)
     * 返回一个全新的CompletableFuture，包含着this或是other操作完成之后，在二者中的任意一个执行fn
     */
    @Test
    public void applyToEitherExample() {

        /*String original = "message";
        CompletableFuture f1 = CompletableFuture.completedFuture(original)
                .thenAcceptAsync(String::toUpperCase);
        CompletableFuture f2 = f1.applyToEither(CompletableFuture.completedFuture(original)
                .thenAcceptAsync(String::toLowerCase), s -> s + " from  applyToEither"
        );
        System.out.println(f2.join());
        assertTrue(((String) f2.join()).endsWith(" from applyToEither"));
*/
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase);
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                s -> s + " from applyToEither");
        assertTrue(cf2.join().toString().endsWith(" from applyToEither"));
    }

    /**
     * 11.消费两个阶段的任意一个结果
     * 和前一个例子类似，将Function替换为Consumer
     */
    @Test
    public void acceptEitherExample() {

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase)
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
    }

    /**
     * 12.在两个阶段都完成后运行Runnable
     * 注意这里的两个Stage都是同步运行的，第一个stage将字符串转化为大写之后，第二个stage将其转化为小写。
     */
    @Test
    public void runAfterBothExample() {

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .runAfterBoth(
                        CompletableFuture.completedFuture(original)
                                .thenAccept(String::toLowerCase),
                        () -> result.append("done"));
        System.out.println(result.toString());
        assertTrue("Result was empty", result.length() > 0);

    }

    /**
     * 13.用Biconsumer接收两个stage的结果
     * Biconsumer支持同时对两个Stage的结果进行操作。
     */
    @Test
    public void thenAcceptBothExample() {

        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }

    /**
     * 14.将Bifunction同时作用于两个阶段的结果
     * 如果CompletableFuture想要合并两个阶段的结果并且返回值，我们可以使用方法thenCombine。
     * 这里的计算流都是同步的，所以最后的getNow()方法会获得最终结果，即大写操作和小写操作的结果的拼接。
     */
    @Test
    public void thenCombineExample() {

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }

    /**
     * 15.异步将Bifunction同时作用于两个阶段的结果
     * 和之前的例子类似，只是这里用了不同的方法：即两个阶段的操作都是异步的。
     * 那么thenCombine也会异步执行，及时它没有Async后缀。
     */
    @Test
    public void thenCombineAsyncExample() {

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * 16.Compose CompletableFuture
     * 我们可以使用thenCompose来完成前两个例子中的操作。
     */
    @Test
    public void thenComposeExample() {

        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(String::toLowerCase)
                        .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * 17.当多个阶段中有有何一个完成，即新建一个完成阶段
     */
    @Test
    public void anyOfExample() {

        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(String::toUpperCase))
                .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if (th == null) {
                result.append(res);
            }
        });
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 18.当所有的阶段完成，新建一个完成阶段
     */
    @Test
    public void allOfExample() {

        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(String::toUpperCase))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
//                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                    result.append("done");
                });
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 19.当所有阶段完成以后，新建一个异步完成阶段
     */
    @Test
    public void allOfAsyncExample() {

        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(String::toUpperCase))
                .collect(Collectors.toList());
        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
//                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                    result.append("done");
                });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
    }


    private void sleepEnough() {

        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void randomSleep() {

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
