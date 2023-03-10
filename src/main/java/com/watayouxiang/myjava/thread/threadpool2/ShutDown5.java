package com.watayouxiang.myjava.thread.threadpool2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDown5 {
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

        List<Runnable> runnableList = threadPool.shutdownNow();
        System.out.println("runnableList = " + runnableList.size());

    }
}
