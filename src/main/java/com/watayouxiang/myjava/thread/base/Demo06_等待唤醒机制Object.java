package com.watayouxiang.myjava.thread.base;

/*
Object类的等待唤醒机制：

	如下方法定义在Object类
	
	void wait() ：让当前线程处于等待状态（例如：mRes.wait(); 那么mRes便是锁对象） 
	void notify() ：唤醒在此锁对象上等待的一个线程 
	void notifyAll() ：唤醒在此锁对象上等待的所有线程 
	
	只有同一个锁上的被等待线程，可以被同一个锁唤醒。不可以对不同锁中的线程进行唤醒。
	也就是说，等待和唤醒必须是同一个锁。
	
	而锁可以是任意对象，所以可以被任意对象调用的方法定义Object类中。
	
IllegalMonitorStateException：

	当调用 object.wait()，object.notify()，object.notifyAll() 之前，
	线程必须拥有 object 锁对象，否则将抛出 IllegalMonitorStateException 异常。
	
	通过以下三种方法之一，线程可以获取 object 锁对象：
	1）通过执行此对象上的 "synchronized方法"
	2）通过执行在此对象上的 "synchronized代码块"
	3）对于 Class 类型的对象，可以通过执行该类的 "synchronized静态方法"

 */
public class Demo06_等待唤醒机制Object {

    public static void main(String[] args) {
        Res res = new Res();

        new Thread(new Producer(res)).start();
        new Thread(new Consumer(res)).start();
    }

    //==============================================================================================

    /**
     * 消费者
     */
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

    /**
     * 消费者
     */
    static class Producer implements Runnable {
        private Res res;

        Producer(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(500);
                    res.produce();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //==============================================================================================

    /**
     * 资源
     */
    static class Res {
        private boolean present = false;//标记

        synchronized void produce() throws InterruptedException {
            // 如果资源存在，则等待
            if (present)
                wait();

            // 如果资源不存在，则生产
            System.out.println(Thread.currentThread().getName() + "，生产一个");
            present = true;

            // 唤醒消费者线程，来消费
            // 如果调用notify()之前，没有释放锁，则会报IllegalMonitorStateException异常
            notify();
        }

        synchronized void consume() throws InterruptedException {
            // 如果资源不存在，则等待消费
            if (!present)
                wait();

            // 如果资源存在，则消费
            System.out.println(Thread.currentThread().getName() + "，消费一个");
            present = false;

            // 唤醒生产者来生成
            // 如果调用notify()之前，没有释放锁，则会报IllegalMonitorStateException异常
            notify();
        }
    }

}