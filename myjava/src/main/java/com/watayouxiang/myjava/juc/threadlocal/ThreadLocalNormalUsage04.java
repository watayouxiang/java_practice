package com.watayouxiang.myjava.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 加锁解决线程安全问题
 */
public class ThreadLocalNormalUsage04 {

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String s;
        synchronized (ThreadLocalNormalUsage04.class) {
            s = format.format(date);
        }
        return s;
    }

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage04().date(finalI);
                    System.out.println("第" + finalI + "个: " + date);
                }
            });
        }
        threadPool.shutdown();
    }

}
