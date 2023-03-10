package com.watayouxiang.myjava.thread.deadlock;

/**
 * 描述：     必定发生死锁的情况
 * <p>
 * 死锁的4个必要条件，缺一不可：
 * 1. 互斥条件                o1、o2 互斥
 * 2. 请求与保持条件           请求 o2，保持 o1
 * 3. 不剥夺条件              线程 r1 和 r2 发生冲突，不会进行剥夺处理
 * 4. 循环等待条件             线程 r1 等待 o2，线程 r2 等待 o1
 */
public class MustDeadLock implements Runnable {

    int flag = 1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}
