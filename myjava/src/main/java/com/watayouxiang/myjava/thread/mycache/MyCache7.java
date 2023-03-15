package com.watayouxiang.myjava.thread.mycache;

import com.watayouxiang.myjava.thread.mycache.computable.Computable;
import com.watayouxiang.myjava.thread.mycache.computable.ExpensiveFunction;

import java.util.concurrent.*;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：1、实现缓存过期功能 2、避免缓存雪崩
 */
public class MyCache7<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MyCache7(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                // 放入缓存，如果缓存中已经存在key，则返回value。如果缓存中不存在，则返回null。
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                    System.out.println("从FutureTask调用了计算函数");
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                cache.remove(arg);
                System.out.println("计算错误，需要重试");
            }
        }
    }

    private final static ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);

    /**
     * 清除过期的缓存
     *
     * @param expire 过期时间，单位毫秒
     */
    public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
        if (expire > 0) {
            exec.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);
                }
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    /**
     * 避免缓存雪崩（缓存同一时间过期，从而导致大量计算需要重新进行）
     */
    public V computeRandomExpire(A arg) throws ExecutionException, InterruptedException {
        long randomExpire = (long) (Math.random() * 10000);
        return compute(arg, randomExpire);
    }

    /**
     * 清除缓存
     */
    private void expire(A arg) {
        Future<V> future = cache.get(arg);
        if (!future.isDone()) {
            System.out.println("过期时间到，Future任务被取消");
            future.cancel(true);
        }
        System.out.println("过期时间到，缓存被清除");
        cache.remove(arg);
    }

    public static void main(String[] args) throws Exception {
        MyCache7<String, Integer> cache = new MyCache7<>(new ExpensiveFunction());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = cache.compute("666", 5000L);
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
                    Integer result = cache.compute("666");
                    System.out.println("第二次计算结果" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

        Thread.sleep(6000L);
        Integer result = cache.compute("666");
        System.out.println("第三次计算结果" + result);
    }
}
