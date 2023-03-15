package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/15
 * <p> description：问题：缓存没办法承载 非常多的线程 同时访问
 */
public class MyCache8 {
    public static void main(String[] args) {

//        int threadCount = 10000;// 此时报OOM异常
        int threadCount = 100;
        MyCache7<String, Integer> myCache = new MyCache7<>(new ExpensiveFunction());
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    result = myCache.compute("666");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(result);
            });
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));

    }
}
