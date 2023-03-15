package com.watayouxiang.myjava.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom = new Candidate();
    static Candidate peter = new Candidate();
    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    static class Candidate {
        // 因为AtomicIntegerFieldUpdater内部是用反射实现的，所以这里不能使用static修饰
//        volatile static int score;

        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("普通变量：" + peter.score);
        System.out.println("升级后的结果：" + tom.score);
    }
}
