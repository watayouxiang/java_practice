package com.watayouxiang.myjava.thread.mycache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：
 */
public class MyCache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer compute(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        // 先检查HashMap里有没有保存过之前的计算结果
        if (result == null) {
            // 如果缓存中找不到，那么需要现在计算一下结果，并且保存到HashMap中
            result = doCompute(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return Integer.parseInt(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        MyCache1 cache1 = new MyCache1();
        System.out.println("开始计算了");
        Integer result = cache1.compute("13");
        System.out.println("第一次计算结果" + result);
        result = cache1.compute("13");
        System.out.println("第二次计算结果" + result);
    }
}
