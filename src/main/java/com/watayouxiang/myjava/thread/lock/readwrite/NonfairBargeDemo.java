package com.watayouxiang.myjava.thread.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示非公平和公平的ReentrantReadWriteLock的策略
 */
public class NonfairBargeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "线程1").start();
        new Thread(() -> read(), "线程2").start();
        new Thread(() -> read(), "线程3").start();
        new Thread(() -> write(), "线程4").start();
        new Thread(() -> read(), "线程5").start();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
