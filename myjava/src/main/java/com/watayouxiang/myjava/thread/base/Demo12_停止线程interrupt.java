package com.watayouxiang.myjava.thread.base;

public class Demo12_停止线程interrupt {

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();

        int x = 0;
        while (true) {
            if (++x == 10) {
                t1.interrupt();
                t2.interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + "-------> over");
    }

    /**
     * 如果线程处于"冻结状态"就需要使用 {@link Thread#interrupt()} 方式将线程恢复到"阻塞状态"/"运行状态"。
     * 此时会抛出 {@link InterruptedException} 异常，在catch中处理停止线程。
     */
    static class Demo implements Runnable {
        private boolean runFlag = true;

        @Override
        public synchronized void run() {
            while (runFlag) {
                System.out.println(Thread.currentThread().getName());
                try {
                    wait();
                } catch (InterruptedException e) {
                    runFlag = false;
                }
            }
            System.out.println(Thread.currentThread().getName() + "-------> over");
        }
    }

}
