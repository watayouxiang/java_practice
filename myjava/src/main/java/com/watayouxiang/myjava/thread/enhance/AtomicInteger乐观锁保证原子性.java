package com.watayouxiang.myjava.thread.enhance;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInteger乐观锁保证原子性 {

    public static void main(String[] args) {
        unsafeIncrease();
        safeIncrease();
    }

    private static AtomicInteger safeCount = new AtomicInteger(0);

    /**
     * 安全的自增
     */
    private static void safeIncrease() {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        safeCount.incrementAndGet();
                    }
                }
            });
            threads[i].start();
        }

        // 返回当前线程的线程组中活动线程的数目
        while (Thread.activeCount() > 1) {
            // 暂停当前正在执行的线程对象，并执行其他线程
            Thread.yield();
        }
        System.out.println("safeCount = " + safeCount);
    }

    private static int unsafeCount = 0;

    /**
     * 不安全的自增
     */
    private static void unsafeIncrease() {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        unsafeCount++;
                    }
                }
            });
            threads[i].start();
        }

        // 返回当前线程的线程组中活动线程的数目
        while (Thread.activeCount() > 1) {
            // 暂停当前正在执行的线程对象，并执行其他线程
            Thread.yield();
        }
        System.out.println("unsafeCount = " + unsafeCount);
    }

}
