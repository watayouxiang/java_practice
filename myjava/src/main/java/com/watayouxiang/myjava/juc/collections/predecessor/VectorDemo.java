package com.watayouxiang.myjava.juc.collections.predecessor;

import java.util.Vector;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：演示Vector，主要看Vector源码
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }
}
