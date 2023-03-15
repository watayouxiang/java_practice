package com.watayouxiang.myjava.thread.mycache.computable;

import java.io.IOException;

/**
 * <p> author：wangtao
 * <p> email：watayouixang@qq.com
 * <p> time：2023/3/15
 * <p> description：耗时计算的实现类，有概率计算失败
 */
public class MayFail implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        if (Math.random() > 0.5) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
