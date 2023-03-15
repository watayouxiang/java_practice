package com.watayouxiang.myjava.thread.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/11
 * description：演示ReentrantLock被打断
 */
public class LockDemo {
    static class Outputer {

        Lock lock = new ReentrantLock();

        // 字符串打印方法，一个个字符串的打印
        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }
    }

    private void init(){
        Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    outputer.output("八戒");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    outputer.output("二师兄");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new LockDemo().init();
    }
}
