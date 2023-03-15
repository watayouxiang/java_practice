package com.watayouxiang.myjava.thread.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：演示公平和非公平两种情况
 */
public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class Job implements Runnable {
    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始打印");
        printQueue.printJob("第一份文件");
        printQueue.printJob("第二份文件");
        System.out.println(Thread.currentThread().getName() + "开始完毕");
    }
}

class PrintQueue {

    // 这里演示公平和非公平锁的差异
    private Lock queueLock = new ReentrantLock(false);

    public void printJob(Object document) {
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName() + "正在打印" + document + "，需要" + duration + "秒");
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }
    }
}
