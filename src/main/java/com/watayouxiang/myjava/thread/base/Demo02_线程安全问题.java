package com.watayouxiang.myjava.thread.base;

/*

问题的原因：
	当多条语句在操作同一个共享数据时，一个线程对多条语句只执行了一部分，
	还没有执行完，另一个线程参与进来执行。导致共享数据的错误。
	
如何找问题：
	1）明确哪些代码是多线程运行代码。
	2）明确共享数据。
	3）明确多线程运行代码中哪些语句是操作共享数据的。

解决办法：
	对多条操作共享数据的语句，只能让一个线程都执行完。
	在执行过程中，其他线程不可以参与执行。
	
 */
public class Demo02_线程安全问题 {

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

        private void saleTicket() {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ", tick = " + ticket--);
            }
        }
    }
}
