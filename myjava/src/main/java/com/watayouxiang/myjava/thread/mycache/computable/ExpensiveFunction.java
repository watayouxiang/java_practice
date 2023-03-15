package com.watayouxiang.myjava.thread.mycache.computable;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：耗时计算的实现类，但是本身不具备缓存能力，不需要考虑缓存的事情。
 */
public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(5000);
        return Integer.parseInt(arg);
    }
}
