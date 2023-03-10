package com.watayouxiang.myjava.thread.threadpool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDown {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new ShutDownTask());
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadPool.isShutdown());
        threadPool.shutdown();
        System.out.println(threadPool.isShutdown());

        // shutdown后，不允许再提交线程
//        threadPool.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
