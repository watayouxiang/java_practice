package com.watayouxiang.myjava.collection;

public class Demo_String {

    public static void main(String[] args) {
        String reverse = reverse("wata");
        System.out.println(reverse);

        String trim = trim("      wata      ");
        System.out.println(trim);

        int subCount = getSubCount("kkabkkcdkkefkks", "kk");
        System.out.println(subCount);

        String maxSubString = getMaxSubString("abcdeqwe", "cdeabrwqedeqwe");
        System.out.println(maxSubString);

    }

    /**
     * 获取两个字符串中最大相同子串
     * 思路：
     * 1）将短的那个子串按照长度递减的方式获取到。
     * 2）将每获取到的子串去长串中判断是否包含，如果包含，已经找到。
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最大相同子串
     */
    private static String getMaxSubString(String s1, String s2) {
        String max = s1.length() > s2.length() ? s1 : s2;
        String min = max.equals(s1) ? s2 : s1;

        /*
        abcdeqwe

        abcdeqw*
        *bcdeqwe

        abcdeq**
        *bcdeqw*
        **cdeqwe

        abcde***
        *bcdeq**
        **cdeqw*
        ***deqwe
         */
        for (int i = 0; i < min.length(); i++) {
            for (int start = 0, end = min.length() - i; end != min.length() + 1; start++, end++) {
                String temp = min.substring(start, end);
                if (max.contains(temp)) {
                    return temp;
                }
            }
        }

        return "";
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数
     *
     * @param str 字符串
     * @param key 关键字字符串
     * @return 出现次数
     */
    private static int getSubCount(String str, String key) {
        int count = 0;// 出现的次数
        int fromIndex = 0;// 指定的索引
        // 返回指定字符第一次出现的字符串内的索引
        while ((fromIndex = str.indexOf(key, fromIndex)) != -1) {
            fromIndex = fromIndex + key.length();
            count++;
        }
        return count;
    }

    /**
     * 去除字符串两端的空格
     *
     * @param str 字符串
     * @return 去两端空字符串后的字符串
     */
    private static String trim(String str) {
        int start = 0, end = str.length() - 1;
        while (start < end && str.charAt(start) == ' ')
            start++;
        while (start < end && str.charAt(end) == ' ')
            end--;
        return str.substring(start, end + 1);
    }

    /**
     * 反转字符串
     *
     * @param str 字符串
     * @return 反转后的字符串
     */
    private static String reverse(String str) {
        char[] arr = str.toCharArray();
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        return new String(arr);
    }

}
