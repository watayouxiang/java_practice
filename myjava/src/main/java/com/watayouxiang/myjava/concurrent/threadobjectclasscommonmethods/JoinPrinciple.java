package com.watayouxiang.myjava.concurrent.threadobjectclasscommonmethods;

/**
 * 描述：     通过讲解join原理，分析出join的代替写法
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }
        });

        thread.start();
        System.out.println("开始等待子线程运行完毕");
//        thread.join();
        synchronized (thread) {
            thread.wait();
            // 通过阅读 native 层源码可以发现：
            // 当 thread 运行结束后，会隐式调用 thread.notifyAll() 方法
        }
        System.out.println("所有子线程执行完毕");
    }
}
