package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.Computable;
import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.HashMap;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：用装饰者模式，用synchronized锁住方法，但存在"并发效率低"问题
 */
public class MyCache2<A, V> implements Computable<A, V> {
    private final HashMap<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public MyCache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            System.out.println("调用了计算函数");
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MyCache2<String, Integer> cache = new MyCache2<>(new ExpensiveFunction());

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
