package com.watayouxiang.myjava.thread.base;

/*

同步的前提：
	 1，必须要有两个或者两个以上的线程。
	 2，必须是多个线程使用同一个锁。
同步的好处和弊端：
	 好处：解决了多线程的安全问题。
	 弊端：多个线程需要判断锁，较为消耗资源

同步函数：
	修饰符 synchronized 返回值类型 函数名(参数列表) {
		需要被同步的代码
	}
	
	注意：
	1）同步函数的锁是this
	2）静态同步函数的锁是Class对象
	
同步代码块：
	synchronized(对象) {
		需要被同步的代码
	}
	
 */
public class Demo03_同步函数 {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();
    }

    static class Ticket implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(10);
                    saleTicket();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 同步函数：同步函数的锁是 this；静态同步函数的锁是 Class 对象；
         */
        private synchronized void saleTicket() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ", tick = " + ticket--);
            }
        }
    }

}