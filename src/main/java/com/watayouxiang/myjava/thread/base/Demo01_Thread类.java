package com.watayouxiang.myjava.thread.base;

public class Demo01_Thread类 {

    public static void main(String[] args) {
//        threadInfo();
//        threadGroup();
//        priority();
//        join();
//        sleep();
        yield();
//        daemon();
//        interrupt();
    }

    /**
     * 打断线程睡眠，让其立即执行
     */
    private static void interrupt() {
        Thread t0 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " sleeping 3 second");
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException ignored) {
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        };
        t0.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " running " + i);
        }
        // 立即打断“线程”，让其恢复到"运行状态"或"阻塞状态"。
        t0.interrupt();
    }

    /**
     * 守护线程
     */
    private static void daemon() {
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            }
        };

        // 设置“thread0线程”为“当前线程”的守护线程
        // 当“当前进程”死亡后，“thread0”也会跟随死亡
        // 当正在运行的线程都是守护线程时，java虚拟机退出。
        thread0.setDaemon(true);
        thread1.setDaemon(true);

        thread0.start();
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " stop");
    }

    /**
     * 线程暂停一会
     */
    private static void yield() {
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    // 暂停一会当前线程（释放执行权），先执行一会其他线程
                    // 让线程执行放缓，增加间隔性
                    Thread.yield();
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    // 暂停一会当前线程（释放执行权），先执行一会其他线程
                    // 让线程执行放缓，增加间隔性
                    Thread.yield();
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            }
        };

        thread0.start();
        thread1.start();
    }

    /**
     * 线程睡眠
     */
    private static void sleep() {
        try {
            // 当前线程睡眠10毫秒
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * join 插队执行
     */
    private static void join() {
        final Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running");
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running");
            }
        };

        thread0.start();

        try {
            // “thread0”插队到“当前线程”之前执行
            // 主线程执行到这里，知道t0要加入执行；主线程释放了执行权，处于"冻结状态"；一旦t0线程执行完后就继续执行
            thread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.start();

        System.out.println(Thread.currentThread().getName() + " running");
    }

    /**
     * 线程优先级
     */
    private static void priority() {
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running");
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running");
            }
        };

        // 设置优先级
        // 默认优先级为5
        // 单核CPU才能看出来
        thread0.setPriority(Thread.MIN_PRIORITY);
        thread1.setPriority(Thread.MAX_PRIORITY);

        thread0.start();
        thread1.start();
    }

    /**
     * 线程组
     */
    private static void threadGroup() {
        // 获取线程组
        // 线程组的好处：可以对同组的线程，进行统一操作
        // 默认都属于main线程组
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);
    }

    /**
     * 线程信息
     */
    private static void threadInfo() {
        // 获取当前线程对象
        Thread currentThread = Thread.currentThread();

        // 获取线程名称
        String name = currentThread.getName();
        System.out.println(name);

        // 包括：线程名称、优先级、线程组
        String string = currentThread.toString();
        System.out.println(string);
    }
}
