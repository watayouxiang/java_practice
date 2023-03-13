package com.watayouxiang.myjava.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示高并发情况下，LongAdder比AtomicLong性能要好
 */
public class LongAdderDemo {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        long during = System.currentTimeMillis() - start;
        System.out.println(counter.get() + "，AtomicLong耗时：" + during);
    }

    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong atomicLong) {
            this.counter = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}

class AtomicAddDemo {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        long during = System.currentTimeMillis() - start;
        System.out.println(counter.sum() + "，LongAdder 耗时：" + during);
    }

    private static class Task implements Runnable {

        private LongAdder counter;

        public Task(LongAdder atomicLong) {
            this.counter = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}