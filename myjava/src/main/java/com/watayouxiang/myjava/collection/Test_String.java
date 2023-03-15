package com.watayouxiang.myjava.collection;

public class Test_String {

    public static void main(String[] args) {
        String reverse = reverse("abcdefg");
        System.out.println(reverse);

        String trim = trim(" hello  ");
        System.out.println(trim);

        int subCount = getSubCount("kkabkkcdkkefkks", "kk");
        System.out.println("出现次数 " + subCount);

        String maxSubStr = getMaxSubStr("abcdeqwe", "cdeabrwqedeqwe");
        System.out.println("最大相同字串 " + maxSubStr);

        constantPool();
    }

    /**
     * String 常量池
     * <p>
     * Java有8种基本数据类型和一种比较特别的类型String。
     * 为了使这些类型在运行更快，更省内存，JAVA为其提供了系统级别的缓存-常量池。
     * <p>
     * JDK6: 常量池放在Perm区中，Perm和正常的JAVA Heap区域是完成分开的
     * JDK7: 字符串常量池移到了JAVA Heap区域
     */
    private static void constantPool() {
        // 存储在字符串常量池中
        String s1 = "abc";

        // 存放JAVA Heap区域
        String s2 = new String("abc");
        // 从字符串常量池中查询当前字符串是否存在，若不存在则将当前字符串放入常量池中
        s2.intern();

        String s3 = new String("1") + new String("1");
        s3.intern();

        String s4 = "11";

        System.out.println((s1 == s2));// false
        System.out.println((s3 == s4));// true
    }

    /**
     * 获取最大字符子串
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最大字符子串
     */
    private static String getMaxSubStr(String s1, String s2) {
        String minStr = s1.length() < s2.length() ? s1 : s2;
        String maxStr = minStr.equals(s1) ? s2 : s1;

        /*
        cdeabrwqedeqwe

        *deabrwqedeqwe
        cdeabrwqedeqw*

        **eabrwqedeqwe
        *deabrwqedeqw*
        cdeabrwqedeq**

        ***abrwqedeqwe
        **eabrwqedeqw*
        *deabrwqedeq**
        cdeabrwqede***
         */

        for (int i = 0; i < minStr.length(); i++) {
            for (int start = 0, end = minStr.length() - i; end != minStr.length() + 1; start++, end++) {
                String temp = minStr.substring(start, end);
                if (maxStr.contains(temp)) {
                    return temp;
                }
            }
        }

        return "";
    }

    /**
     * 一个字符串在另一个字符串中出现的次数
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 出现的次数
     */
    private static int getSubCount(String s1, String s2) {
        // int index = str.index(key, fromIndex);
        String key = s1.length() < s2.length() ? s1 : s2;
        String str = key.equals(s1) ? s2 : s1;

        int count = 0;
        int fromIndex = 0;
        while ((fromIndex = str.indexOf(key, fromIndex)) != -1) {
            fromIndex += key.length();
            count++;
        }

        return count;
    }

    /**
     * 去除字符串两端的空字符
     *
     * @param str 字符串
     * @return 处理后的字符串
     */
    private static String trim(String str) {
        char[] chs = str.toCharArray();
        int start = 0;
        int end = chs.length - 1;

        while (start < end && chs[start] == ' ') start++;
        while (start < end && chs[end] == ' ') end--;

        return str.substring(start, end + 1);
    }

    /**
     * 反转字符串
     *
     * @param str 字符串
     * @return 反转后的字符串
     */
    private static String reverse(String str) {
        char[] chs = str.toCharArray();
        for (int start = 0, end = chs.length - 1; start < end; start++, end--) {
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;
        }
        return new String(chs);
    }
}
