package com.watayouxiang.myjava.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSample3 {
    public static void main(String[] args) {
        // 调度器对象
        // ExecutorService 用于管理线程池
        // 单线程线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 1000; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                }
            });
        }
        threadPool.shutdown();
    }
}
