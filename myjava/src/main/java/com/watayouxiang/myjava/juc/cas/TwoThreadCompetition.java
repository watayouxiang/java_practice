package com.watayouxiang.myjava.juc.cas;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：
 */
public class TwoThreadCompetition implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetition r = new TwoThreadCompetition();
        r.value = 0;
        Thread thread1 = new Thread(r, "线程1");
        Thread thread2 = new Thread(r, "线程2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.value);
    }
}
