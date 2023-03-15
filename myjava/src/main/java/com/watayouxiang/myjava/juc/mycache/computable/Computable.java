package com.watayouxiang.myjava.juc.mycache.computable;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/14
 * <p> description：有一个计算函数compute，用来代表耗时计算，每个计算器都需要实现这个接口，这样就可以无侵入实现缓存功能。
 */
public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}
