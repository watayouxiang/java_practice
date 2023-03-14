package com.watayouxiang.myjava.thread.future;

import java.util.concurrent.*;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：演示get方法抛出异常的时机：并不是说一产生异常就抛出，直到我们执行get方法时，才会抛出。
 */
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);

        Future<Integer> future = service.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            future.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException异常");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("ExecutionException异常");
            throw new RuntimeException(e);
        }

        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
