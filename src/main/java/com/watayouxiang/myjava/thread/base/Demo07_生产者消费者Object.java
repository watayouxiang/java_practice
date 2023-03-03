package com.watayouxiang.myjava.thread.base;

/*

生产者和消费者问题：（用 “Object类的等待唤醒机制” 来解决）

有多个线程同时对同一资源进行数据操作。
多个生产者线程，多个消费者线程。

 */
public class Demo07_生产者消费者Object {

    public static void main(String[] args) {
        Res res = new Res();

        Producer producer = new Producer(res);
        Consumer consumer = new Consumer(res);

        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    //==============================================================================================

    static class Consumer implements Runnable {
        private Res res;

        Consumer(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(500);
                    res.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable {
        private Res res;

        Producer(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(100);
                    res.produce();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //==============================================================================================

    static class Res {
        private int count = 0;

        synchronized void produce() throws InterruptedException {
            // 问：为什么要定义while判断标记？
            // 答：对于多个生产者和消费者，为了避免多次生产，被唤醒的线程需要再一次判断标记
            while (count >= 10)
                wait();

            count++;
            System.out.println(Thread.currentThread().getName() + "，生产一个，count=" + count);

            // 问：为什么要用notifyAll()，而不是 notify()？
            // 答：因为用notify，如果出现只唤醒本方线程的情况，会导致程序中的所有线程都等待（死锁）
            notifyAll();
        }

        synchronized void consume() throws InterruptedException {
            while (count <= 0)
                wait();

            count--;
            System.out.println(Thread.currentThread().getName() + "，消费一个，count=" + count);

            notifyAll();
        }
    }

}