package com.watayouxiang.myjava.juc.threadpool2;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

        // 5秒后执行
//        threadPool.schedule(new Task(), 5, TimeUnit.SECONDS);

        // 1秒后执行，之后每隔3秒执行一次
        threadPool.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}


