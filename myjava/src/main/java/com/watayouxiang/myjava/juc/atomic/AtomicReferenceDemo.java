package com.watayouxiang.myjava.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示 AtomicReference 的使用
 */
public class AtomicReferenceDemo {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!sign.compareAndSet(null, currentThread)) {
//            System.out.println("自旋获取失败，再次尝试...");
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        sign.compareAndSet(currentThread, null);
    }

    public static void main(String[] args) {
        AtomicReferenceDemo spinLock = new AtomicReferenceDemo();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                    spinLock.unlock();
                }
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread.start();
        thread2.start();


    }
}
