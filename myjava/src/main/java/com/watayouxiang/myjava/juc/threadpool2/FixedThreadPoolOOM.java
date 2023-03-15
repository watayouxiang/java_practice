package com.watayouxiang.myjava.juc.threadpool2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolOOM {
    public static void main(String[] args) {
        // 设置 VM options 为： -Xmx8m -Xms8m
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.execute(new Task());
        }
    }
}


