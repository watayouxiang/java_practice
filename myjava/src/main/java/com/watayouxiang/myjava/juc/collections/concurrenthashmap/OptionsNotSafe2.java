package com.watayouxiang.myjava.juc.collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：ConcurrentHashMap组合操作的正确使用
 */
public class OptionsNotSafe2 implements Runnable {
    private static final ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe2());
        Thread t2 = new Thread(new OptionsNotSafe2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                int newScore = score + 1;
                boolean ok = scores.replace("小明", score, newScore);
                if (ok) {
                    break;
                }
            }
        }
    }
}
