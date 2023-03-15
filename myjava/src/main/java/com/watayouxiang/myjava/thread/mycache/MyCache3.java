package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.Computable;
import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：用ConcurrentHashMap提高了并发效率，但存在"重复计算"问题
 */
public class MyCache3<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MyCache3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            System.out.println("调用了计算函数");
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MyCache3<String, Integer> cache = new MyCache3<>(new ExpensiveFunction());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = cache.compute("666");
                    System.out.println("第一次计算结果" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = cache.compute("667");
                    System.out.println("第二次计算结果" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = cache.compute("666");
                    System.out.println("第三次计算结果" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }
}
