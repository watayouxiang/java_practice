package com.watayouxiang.myjava.thread.threadpool2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            service.execute(new Task());
        }
    }
}


