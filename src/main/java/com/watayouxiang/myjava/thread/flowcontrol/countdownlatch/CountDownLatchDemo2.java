package com.watayouxiang.myjava.thread.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：多等一的场景
 * <p>
 * 模拟100米跑步，5名选手都准备好了，只等待裁判员一声令下，所有人同时开始跑步。
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令枪");
                    try {
                        latch.await();
                        System.out.println("No." + no + "开始跑步了");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                }
            };
            service.submit(runnable);
        }

        System.out.println("裁判员检查发令枪...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发令枪响，比赛开始！");
        latch.countDown();
    }
}
