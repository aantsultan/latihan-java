package com.latihan.java.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ForkJoinPoolTest {

    @Test
    void createTest(){
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinPool pool2 = new ForkJoinPool(5);

        ExecutorService executorService = Executors.newWorkStealingPool();
        ExecutorService executorService2 = Executors.newWorkStealingPool(5);
    }

    @Test
    void recursiveActionTest() throws InterruptedException {
//        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinPool pool = new ForkJoinPool(5);
        List<Integer> collect = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        SimpleForkJoinTask task = new SimpleForkJoinTask(collect);

        pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void recursiveTaskTest() throws InterruptedException, ExecutionException {
//        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinPool pool = new ForkJoinPool(5);
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        TotalRecursiveTask task = new TotalRecursiveTask(integers);
        ForkJoinTask<Long> submit = pool.submit(task);

        Long result = submit.get();
        System.out.println(result);

        long expectation = integers.stream().mapToLong(value -> value).sum();
        Assertions.assertEquals(expectation, result);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    public static class SimpleForkJoinTask extends RecursiveAction {
        private List<Integer> integers;

        public SimpleForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            if(integers.size() <= 10){
                // eksekusi
                doExecute();
            } else {
                // fork
                forCompute();
            }
        }

        private void forCompute() {
            List<Integer> integers1 = this.integers.subList(0, integers.size() / 2);
            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1, task2);
        }

        private void doExecute() {
            integers.forEach(integer -> 
                    System.out.println(Thread.currentThread().getId() + ":"+ integer));
        }
    }

    public static class TotalRecursiveTask extends RecursiveTask<Long> {

        private List<Integer> integers;

        public TotalRecursiveTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if(integers.size() <= 10){
                return doCompute();
            } else {
                return forCompute();
            }
        }

        private Long forCompute() {
            List<Integer> integers1 = this.integers.subList(0, integers.size() / 2);
            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

            TotalRecursiveTask task = new TotalRecursiveTask(integers1);
            TotalRecursiveTask task2 = new TotalRecursiveTask(integers2);

            ForkJoinTask.invokeAll(task, task2);
            return task.join() + task2.join();
        }

        private Long doCompute() {
            return integers.stream().mapToLong(value -> value).peek( value -> {
                System.out.println(Thread.currentThread().getName() + " : "+ value);
            }).sum();
        }
    }
}
