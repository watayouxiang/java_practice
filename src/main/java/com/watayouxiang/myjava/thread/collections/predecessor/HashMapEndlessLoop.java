package com.watayouxiang.myjava.thread.collections.predecessor;

import java.util.HashMap;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：演示HashMap在多线程下造成死循环的情况（需要在 Java 1.7 版本下演示）
 */
public class HashMapEndlessLoop {

    // 2 * 1.5 = 3
    // 当元素为3时不会扩容，当元素为4时需要扩容
    // 在 Java 1.7 590行 和 594行 打上断点，设置针对线程调试，让Thread1和Thread2分别进入到 594行，此时发生OOM
    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 1.5f);

    public static void main(String[] args) {
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread2").start();
    }
}
