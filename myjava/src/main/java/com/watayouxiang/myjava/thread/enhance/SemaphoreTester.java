package com.watayouxiang.myjava.thread.enhance;

import java.util.concurrent.Semaphore;

public class SemaphoreTester {

    public static void main(String[] args) {
        SemaphoreTester tester = new SemaphoreTester();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tester.test01();
                }
            }).start();
        }


        tester.test02();


    }

    /**
     * 三个线程 a、b、c 并发运行，b,c 需要 a 线程的数据怎么实现?
     */
    private void test02() {

    }

    private static Semaphore semaphore = new Semaphore(5, true);

    /**
     * 如何控制某个方法允许并发访问线程的个数?
     * <p>
     * 可以使用 Semaphore 控制，并且初始化了5个信号，这样最多只能有 5 个线程并发访问
     */
    private void test01() {
        try {
            // 申请一个请求
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "进来了");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "走了");

        // 释放一个请求
        semaphore.release();
    }
}
