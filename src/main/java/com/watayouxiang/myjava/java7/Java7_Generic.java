package com.watayouxiang.myjava.java7;

import java.util.ArrayList;
import java.util.Iterator;

public class Java7_Generic {
    /**
     * 新版泛型
     */
    public static void main(String[] args) {
        /*
         * java5加入了泛型机制，提高了安全性
         * 但是带来了书写的麻烦
         * java7简化了这个机制，左边定义类型，右边不需要定义类型了，直接写<>
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("_123_");

        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String next = it.next();
            System.out.println(next);
        }
    }
}
