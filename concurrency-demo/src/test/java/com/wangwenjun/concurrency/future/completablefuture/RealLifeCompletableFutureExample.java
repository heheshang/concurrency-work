package com.wangwenjun.concurrency.future.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * 20.真实场景
 * 下面展示了一个实践CompletableFuture的场景：
 * <p>
 * 先通过调用cars()方法异步获得Car列表。它将会返回一个CompletionStage<List<Car>>。cars()方法应当使用一个远程的REST端点来实现。
 * 我们将该Stage和另一个Stage组合，另一个Stage会通过调用rating(manufactureId)来异步获取每辆车的评分。
 * 当所有的Car对象都填入评分后，我们调用allOf()来进入最终Stage，它将在这两个阶段完成后执行
 * 在最终Stage上使用whenComplete()，打印出车辆的评分。
 * <p>
 * cars().thenCompose(cars -> {
 * List<CompletionStage> updatedCars = cars.stream()
 * .map(car -> rating(car.manufacturerId).thenApply(r -> {
 * car.setRating(r);
 * return car;
 * })).collect(Collectors.toList());
 * CompletableFuture done = CompletableFuture
 * .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
 * return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
 * .map(CompletableFuture::join).collect(Collectors.toList()));
 * }).whenComplete((cars, th) -> {
 * if (th == null) {
 * cars.forEach(System.out::println);
 * } else {
 * throw new RuntimeException(th);
 * }
 * }).toCompletableFuture().join();
 */
public class RealLifeCompletableFutureExample {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        cars().thenCompose(cars -> {
            List<CompletionStage<Car>> updatedCars = cars.stream()
                    .map(
                            car -> rating(car.manufacturerId).thenApply(r -> {
                                car.setRating(r);
                                return car;
                            })
                    ).collect(Collectors.toList());

            CompletableFuture<Void> done = CompletableFuture
                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if (th == null) {
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();

        long end = System.currentTimeMillis();

        System.out.println("Took " + (end - start) + " ms.");
    }

    static CompletionStage<Float> rating(int manufacturer) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                simulateDelay();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            switch (manufacturer) {
                case 2:
                    return 4f;
                case 3:
                    return 4.1f;
                case 7:
                    return 4.2f;
                default:
                    return 5f;
            }
        }).exceptionally(th -> -1f);
    }

    static CompletionStage<List<Car>> cars() {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, 3, "Fiesta", 2017));
        carList.add(new Car(2, 7, "Camry", 2014));
        carList.add(new Car(3, 2, "M2", 2008));
        return CompletableFuture.supplyAsync(() -> carList);
    }

    private static void simulateDelay() throws InterruptedException {

        Thread.sleep(5000);
    }
}
