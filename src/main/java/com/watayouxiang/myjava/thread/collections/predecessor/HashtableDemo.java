package com.watayouxiang.myjava.thread.collections.predecessor;

import java.util.Hashtable;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：
 */
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("学号", "11024");
        System.out.println(hashtable.get("学号"));

    }
}
