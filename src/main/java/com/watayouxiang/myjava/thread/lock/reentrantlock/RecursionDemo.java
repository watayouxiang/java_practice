package com.watayouxiang.myjava.thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：可重入锁在递归调用中的应用
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println("before - " + lock.getHoldCount());
                accessResource();
                System.out.println("after - " + lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }

}
