package com.watayouxiang.myjava.thread.threadcoreknowledge.threaderror;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：     第一种：运行结果出错。 演示计数不准确（减少），找出具体出错的位置。
 */
public class MultiThreadsError implements Runnable {

    static MultiThreadsError instance = new MultiThreadsError();
    int index = 0;
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    // CyclicBarrier 用于协调多个线程同步执行操作的场合，所有线程等待完成,然后一起做事情。
    // 例如百米赛跑，必须等待所有运动员都准备好了，才能比赛。
    // 运动员准备好，裁判也准备好，才能开始正式比赛。
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    final boolean[] marked = new boolean[10000000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是" + instance.index);
        System.out.println("真正运行的次数" + realIndex.get());
        System.out.println("错误次数" + wrongCount.get());

    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                // 重置 cyclicBarrier2
                cyclicBarrier2.reset();
                // 只有两个线程同时到达此处时才能往下执行
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                // 重置 cyclicBarrier1
                cyclicBarrier1.reset();
                // 只有两个线程同时到达此处时才能往下执行
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
