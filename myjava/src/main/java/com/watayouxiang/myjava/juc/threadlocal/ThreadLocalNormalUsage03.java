package com.watayouxiang.myjava.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 出现重复日期：出现线程安全问题
 */
public class ThreadLocalNormalUsage03 {

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return format.format(date);
    }

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println("第" + finalI + "个: " + date);
                }
            });
        }
        threadPool.shutdown();
    }

}
