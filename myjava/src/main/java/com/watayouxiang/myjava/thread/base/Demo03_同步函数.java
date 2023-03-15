package com.watayouxiang.myjava.thread.base;

/**

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

    static class Cinema {
        private int ticket = 3;

        /**
         * 同步函数：同步函数的锁是 this；静态同步函数的锁是 Class 对象；
         */
        synchronized void saleTicket() {
            if (ticket > 0) {
                ticket--;
                System.out.println("当前剩余票数：" + ticket);
            }
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cinema.saleTicket();
                }
            }).start();
        }
    }
}