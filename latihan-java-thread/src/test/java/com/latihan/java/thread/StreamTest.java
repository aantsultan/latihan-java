package com.latihan.java.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class StreamTest {

    @Test
    void parallelTest(){
        Stream<Integer> stream = IntStream.range(0, 1000).boxed();

        stream.parallel().forEach( integer -> {
            System.out.println(Thread.currentThread().getName() + " : "+ integer);
        });
    }

    @Test
    void customPoolTest(){
        ForkJoinPool pool = new ForkJoinPool(5);
        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 1000).boxed();
            stream.parallel().forEach(integer -> {
                System.out.println(Thread.currentThread().getName() + " : " + integer);
            });
        });

        task.join();
    }

}
