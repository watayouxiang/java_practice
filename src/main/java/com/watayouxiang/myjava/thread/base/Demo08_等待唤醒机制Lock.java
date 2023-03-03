package com.watayouxiang.myjava.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

Lock类的等待唤醒机制：（JDK1.5 中提供的多线程升级解决方案）

	将同步Synchronized替换成现实Lock操作
	Lock.lock()//获取锁
	Lock.unlock//释放锁
	Condition condition = lock.newCondition()//获取环境
	
	将 Object 中的 wait，notify，notifyAll，
	替换了 Condition 对象的 await，signal，signalAll
	condition.await();//线程等待
	condition.signal();//唤醒一个线程
	condition.signalAll();//唤醒所有线程
	
 */
public class Demo08_等待唤醒机制Lock {

    public static void main(String[] args) {
        Res res = new Res();

        Producer producer = new Producer(res);
        Consumer consumer = new Consumer(res);

        new Thread(producer).start();
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
        private boolean present = false;//标记

        private Lock lock = new ReentrantLock();
        private Condition condition_producer = lock.newCondition();
        private Condition condition_consumer = lock.newCondition();

        void produce() throws InterruptedException {
            try {
                // 获取锁
                lock.lock();

                // 如果存在，则將生产者线程置为等待状态
                if (present)
                    condition_producer.await();

                present = true;
                System.out.println(Thread.currentThread().getName() + "，生产一个");

                // 唤醒消费者
                // 如果调用signal()前，没有lock.unlock()，则会报IllegalMonitorStateException异常
                condition_consumer.signal();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }

        void consume() throws InterruptedException {
            try {
                // 获取锁
                lock.lock();

                // 如果不存在，则将消费者线程置为等待状态
                if (!present)
                    condition_consumer.await();

                present = false;
                System.out.println(Thread.currentThread().getName() + "，消费一个");

                // 唤醒生产者
                // 如果调用signal()前，没有lock.unlock()，则会报IllegalMonitorStateException异常
                condition_producer.signal();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

}