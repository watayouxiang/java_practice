package com.watayouxiang.myjava.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

生产者和消费者问题：（用 “Lock类的等待唤醒机制” 来解决）

有多个线程同时对同一资源进行数据操作。
多个生产者线程，多个消费者线程。


 */
public class Demo09_生产者消费者Lock {

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

        private Lock lock = new ReentrantLock();
        private Condition condition_producer = lock.newCondition();
        private Condition condition_consumer = lock.newCondition();

        void produce() throws InterruptedException {
            try {
                lock.lock();

                while (count >= 10)
                    condition_producer.await();

                count++;
                System.out.println(Thread.currentThread().getName() + "，生产一个，count=" + count);

                condition_consumer.signalAll();
            } finally {
                lock.unlock();
            }
        }

        void consume() throws InterruptedException {
            try {
                lock.lock();

                while (count <= 0)
                    condition_consumer.await();

                count--;
                System.out.println(Thread.currentThread().getName() + "，消费一个，count=" + count);

                condition_producer.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}