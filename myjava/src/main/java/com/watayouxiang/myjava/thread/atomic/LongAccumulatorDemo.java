package com.watayouxiang.myjava.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：演示 LongAccumulator(累加器) 的用法
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
//        LongAccumulator accumulator = new LongAccumulator((x, y) -> Math.max(x, y), 100);
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 100);

//        // 100 + 1 = 101
//        accumulator.accumulate(1);
//        // 101 + 2 = 103
//        accumulator.accumulate(2);
//        System.out.println(accumulator.getThenReset());

        ExecutorService service = Executors.newFixedThreadPool(8);
        // 1+2+3+...+9
        IntStream.range(1, 10).forEach(i -> service.submit(() -> accumulator.accumulate(i)));
        service.shutdown();
        while (!service.isTerminated()) {
        }
        System.out.println(accumulator.getThenReset());
    }
}
