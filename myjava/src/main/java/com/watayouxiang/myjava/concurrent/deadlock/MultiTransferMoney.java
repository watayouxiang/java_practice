package com.watayouxiang.myjava.concurrent.deadlock;

import com.watayouxiang.myjava.concurrent.deadlock.TransferMoney.Account;

import java.util.Random;

/**
 * 描述：     多人同时转账，依然很危险
 */
public class MultiTransferMoney {

    // 账户数量
    private static final int NUM_ACCOUNTS = 500;
    // 金额
    private static final int NUM_MONEY = 1000;
    // 转账次数
    private static final int NUM_ITERATIONS = 1000000;
    // 线程数量
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        // 500个账户，每个账户有有1000元
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        // 进行 100万次 的相互转账
        Random rnd = new Random();
        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("运行结束");
            }
        }

        // 20个线程运行
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
