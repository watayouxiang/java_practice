package com.watayouxiang.myjava.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示AtomicInteger的基本用法，对比非原子类的线程安全问题，不需要加锁，也能保证线程安全
 */
public class AtomicIntegerDemo1 implements Runnable {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    private volatile int basicCount = 0;

    public synchronized void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类的结果：" + r.atomicInteger.get());
        System.out.println("普通变量的结果：" + r.basicCount);
    }
}
