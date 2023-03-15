package com.watayouxiang.myjava.thread.deadlock;

/**
 * 描述：     转账时候遇到死锁
 * <p>
 * 死锁的4个必要条件，缺一不可：
 * 1. 互斥条件                from、to 互斥
 * 2. 请求与保持条件           请求 to，保持 from
 * 3. 不剥夺条件              线程 t1 和 t2 发生冲突，不会进行剥夺处理
 * 4. 循环等待条件             线程 t1 等待 to，线程 t2 等待 from
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        synchronized (from) {

            // 模拟耗时操作，使发生死锁
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                    return;
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账" + amount + "元");
            }
        }
    }


    static class Account {

        public Account(int balance) {
            this.balance = balance;
        }

        int balance;

    }
}
