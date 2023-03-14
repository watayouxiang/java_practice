package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.Computable;
import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.HashMap;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：用装饰者模式，给计算器自动添加缓存功能
 */
public class MyCache3<A, V> implements Computable<A, V> {
    private final HashMap<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public MyCache3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    // 请在 java 1.8 下运行
    public static void main(String[] args) {
        MyCache3<String, Integer> cache = new MyCache3<>(new ExpensiveFunction());
        System.out.println("开始计算了");

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
