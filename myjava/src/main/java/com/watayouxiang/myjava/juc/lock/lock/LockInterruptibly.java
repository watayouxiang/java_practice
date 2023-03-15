package com.watayouxiang.myjava.juc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：lock.lockInterruptibly(); 相当于把 lock1.tryLock(800, TimeUnit.MILLISECONDS)
 * 超时时间设置为无限。在等待锁期间，可以被中断。
 */
public class LockInterruptibly implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + "尝试获取锁");
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "拿到了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获取锁期间被中断");
        }
    }

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread = new Thread(lockInterruptibly);
        Thread thread1 = new Thread(lockInterruptibly);
        thread.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
    }
}
