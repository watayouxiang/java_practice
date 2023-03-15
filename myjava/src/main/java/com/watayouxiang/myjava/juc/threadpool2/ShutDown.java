package com.watayouxiang.myjava.juc.threadpool2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDown {
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

//        threadPool.shutdown();
//        // shutdown后，不允许再提交线程
//        // threadPool.execute(new ShutDownTask());
//        System.out.println("isShutdown = " + threadPool.isShutdown());
//        System.out.println("isTerminated = " + threadPool.isTerminated());
//        try {
//            System.out.println("awaitTermination = " + threadPool.awaitTermination(10, TimeUnit.SECONDS));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        List<Runnable> runnableList = threadPool.shutdownNow();
        System.out.println("runnableList = " + runnableList.size());

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
