package com.watayouxiang.myjava.thread.collections.predecessor;

import java.util.HashMap;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：演示Map的基本用法
 */
public class MapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("东哥", 38);
        map.put("浩哥", 28);
        System.out.println(map.keySet());
        System.out.println(map.get("东哥"));
        System.out.println(map.size());
        System.out.println(map.containsKey("东哥"));
        map.remove("东哥");
        System.out.println(map.containsKey("东哥"));
    }
}
