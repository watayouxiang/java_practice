package com.watayouxiang.myjava.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest2 {

    private static ExecutorService executorService;

    public static void main(String[] args) {
        executorService = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 1000; i++) {
            connect();
            disconnect();
        }
    }

    private synchronized static void disconnect() {
        executorService.shutdownNow();
    }

    private synchronized static void connect() {
        executorService.execute(new MyThread("1"));
        executorService.execute(new MyThread("2"));
    }

    private static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            System.out.println("end");
        }
    }
}
