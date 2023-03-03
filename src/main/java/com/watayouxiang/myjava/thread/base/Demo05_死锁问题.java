package com.watayouxiang.myjava.thread.base;

/*

死锁：同步中嵌套同步

Thread-0 拿到了 lockA，Thread-1 拿到了 lockB；
Thread-0 等待 lockB，Thread-1 等待 lockA；
互相等待对方释放锁，从而导致死锁。

*/
public class Demo05_死锁问题 {

    public static void main(String[] args) {
        //t1线程执行if中的代码块
        new Thread(new Ticket(true)).start();
        //t2线程执行else中的代码块
        new Thread(new Ticket(false)).start();
    }

    static class Ticket implements Runnable {
        private boolean flag;

        Ticket(boolean flag) {
            this.flag = flag;
        }

        public void run() {
            if (flag) {
                while (true) {
                    synchronized (MyLock.lockA) {//外同步
                        System.out.println(Thread.currentThread().getName() + " get lockA");
                        synchronized (MyLock.lockB) {//内同步
                            System.out.println(Thread.currentThread().getName() + " get lockB");
                        }
                    }
                }
            } else {
                while (true) {
                    synchronized (MyLock.lockB) {//外同步
                        System.out.println(Thread.currentThread().getName() + " get lockB");
                        synchronized (MyLock.lockA) {//内同步
                            System.out.println(Thread.currentThread().getName() + " get lockA");
                        }
                    }
                }
            }
        }
    }

    static class MyLock {
        static final Object lockA = new Object();
        static final Object lockB = new Object();
    }
}