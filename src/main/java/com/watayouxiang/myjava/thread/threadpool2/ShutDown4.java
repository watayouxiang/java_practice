package com.watayouxiang.myjava.thread.threadpool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDown4 {
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

        threadPool.shutdown();
        try {
            System.out.println("isTerminated = " + threadPool.awaitTermination(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
