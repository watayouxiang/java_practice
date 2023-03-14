package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.Computable;
import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.HashMap;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：缩小了synchronized的粒度，提高了性能，但是依然并发不安全
 */
public class MyCache4<A, V> implements Computable<A, V> {
    private final HashMap<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public MyCache4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            synchronized (this) {
                cache.put(arg, result);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        MyCache4<String, Integer> myCache2 = new MyCache4<>(new ExpensiveFunction());
        System.out.println("开始计算了");
        Integer result = myCache2.compute("666");
        System.out.println("第一次计算结果" + result);
        result = myCache2.compute("666");
        System.out.println("第二次计算结果" + result);
    }
}
