package com.watayouxiang.myjava.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：自己用AQS实现一个简单的线程协作器
 */
public class OneAQSLatch {
    private final Sync sync = new Sync();

    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneAQSLatch oneAQSLatch = new OneAQSLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                    oneAQSLatch.await();
                    System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
                }
            }).start();
        }

        Thread.sleep(2000);
        oneAQSLatch.signal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                oneAQSLatch.await();
                System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
            }
        }, "[开闸后的线程]").start();
    }
}
