package com.watayouxiang.myjava.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示 AtomicIntegerArray
 */
public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(100);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] incrementerThreads = new Thread[atomicIntegerArray.length()];
        Thread[] decrementerThreads = new Thread[atomicIntegerArray.length()];

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            incrementerThreads[i] = new Thread(incrementer);
            decrementerThreads[i] = new Thread(decrementer);
            incrementerThreads[i].start();
            decrementerThreads[i].start();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            try {
                incrementerThreads[i].join();
                decrementerThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误" + i);
            }
        }
        System.out.println("运行结束");


    }
}

class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}

class Decrementer implements Runnable {

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}