package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/15
 * <p> description：利用ThreadLocal打印每一个线程的开始计算时间
 */
public class MyCache10 {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 100;
        MyCache7<String, Integer> myCache = new MyCache7<>(new ExpensiveFunction());
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < threadCount; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    latch.await();
                    String time = ThreadLocalFormatter.dateFormatter.get().format(new Date());
                    System.out.println(Thread.currentThread().getName() + " time=" + time + "被放行");
                    result = myCache.compute("666");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(result);
            });
        }

        Thread.sleep(5000);
        latch.countDown();
        service.shutdown();

    }
}

class ThreadLocalFormatter {
    public static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };
}
