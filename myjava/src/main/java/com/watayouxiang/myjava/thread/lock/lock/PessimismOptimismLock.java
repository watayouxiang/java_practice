package com.watayouxiang.myjava.thread.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：悲观锁 和 乐观锁
 */
public class PessimismOptimismLock {

    int a = 0;

    public synchronized void testMethod() {
        a++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) {
        PessimismOptimismLock pessimismOptimismLock = new PessimismOptimismLock();

        // 乐观锁
        pessimismOptimismLock.atomicInteger.incrementAndGet();

        // 悲观锁
        pessimismOptimismLock.testMethod();
    }
}
