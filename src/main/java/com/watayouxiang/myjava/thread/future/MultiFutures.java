package com.watayouxiang.myjava.thread.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：
 */
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                // 阻塞式获取执行结果
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return new Random().nextInt();
        }
    }
}
