package com.watayouxiang.myjava.thread.collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：组合操作并不保证线程安全
 */
public class OptionsNotSafe implements Runnable {
    private static final ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            // ConcurrentHashMap 能保证单个操作是线程安全的
            // 比如 scores.get("小明"); / scores.put("小明", newScore);
            // 但如下代码是组合操作，所以线程不安全
            Integer score = scores.get("小明");
            int newScore = score + 1;
            scores.put("小明", newScore);
        }
    }
}
