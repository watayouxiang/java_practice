package com.watayouxiang.myjava.thread.base;

public class Demo11_停止线程flag {

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();

        int x = 0;
        while (true) {
            if (++x == 10) {
                demo.flagStop();
                break;
            }
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + "-------> over");
    }

    /**
     * 通过改变线程代码的"标记位"来停止线程
     */
    static class Demo implements Runnable {
        private boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + "-------> over");
        }

        void flagStop() {
            runFlag = false;
        }
    }

}
