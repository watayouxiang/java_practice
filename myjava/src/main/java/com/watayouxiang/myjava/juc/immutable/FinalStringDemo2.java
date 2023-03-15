package com.watayouxiang.myjava.juc.immutable;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：
 */
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        String b = getDashixiong();
        String d = "wukong";
        String c = b + 2;
        System.out.println((a == c));
    }

    private static String getDashixiong() {
        return "wukong";
    }
}
