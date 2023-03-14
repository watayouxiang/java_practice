package com.watayouxiang.myjava.thread.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：多等一的场景 + 一等多的场景
 * <p>
 * 模拟100米跑步，5名选手都准备好了，只等待裁判员一声令下，所有人同时开始跑步。所有人都到终点后，比赛结束。
 */
public class CountDownLatchDemo1And2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("No." + no + "开始跑步了");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + "到达终点");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        end.countDown();
                    }
                }
            };
            service.submit(runnable);
        }

        System.out.println("裁判员检查发令枪...");
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();

        end.await();
        System.out.println("所有人到达终点，比赛结束。");
    }
}
