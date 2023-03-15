package com.watayouxiang.myjava.thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：可重入锁 - 获得锁的次数
 */
public class GetHoldCount {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());

    }
}
