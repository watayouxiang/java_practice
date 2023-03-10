package com.watayouxiang.myjava.thread.threadpool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDown3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new ShutDownTask());
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("isTerminated = " + threadPool.isTerminated());
        threadPool.shutdown();
        System.out.println("isTerminated = " + threadPool.isTerminated());

    }
}
