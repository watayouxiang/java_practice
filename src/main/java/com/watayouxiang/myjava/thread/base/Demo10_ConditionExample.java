package com.watayouxiang.myjava.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo10_ConditionExample {

    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    /**
     * 生产者
     */
    private static class Producer implements Runnable {
        private BoundedBuffer buffer;

        private Producer(BoundedBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) buffer.put("MacBook-Pro");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    private static class Consumer implements Runnable {
        private BoundedBuffer buffer;

        private Consumer(BoundedBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) buffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@link Condition} 中的示例代码
     */
    private static class BoundedBuffer {
        private final Lock lock = new ReentrantLock();//锁
        private final Condition producer = lock.newCondition();//生产者
        private final Condition consumer = lock.newCondition();//消费者

        private final Object[] items = new Object[100];//容器
        private int putPtr;//生产者角标
        private int takePtr;//消费者角标
        private int count;//产品数量

        private void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                //容器满了，则停止生产
                while (count == items.length)
                    producer.await();
                //存放产品
                items[putPtr] = x;
                //重置指针
                if (++putPtr == items.length) putPtr = 0;
                //产品数量+1
                ++count;
                System.out.println(Thread.currentThread().getName() + "，生产一个，count = " + count);
                //唤醒消费者
                consumer.signal();
            } finally {
                lock.unlock();
            }
        }

        private Object take() throws InterruptedException {
            lock.lock();
            try {
                //容器空了，则停止消费
                while (count == 0)
                    consumer.await();
                //消费产品
                Object x = items[takePtr];
                //重置指针
                if (++takePtr == items.length) takePtr = 0;
                //产品数量-1
                --count;
                System.out.println(Thread.currentThread().getName() + "，消费一个，count = " + count);
                //唤醒生产者
                producer.signal();
                //返回产品
                return x;
            } finally {
                lock.unlock();
            }
        }
    }

}
