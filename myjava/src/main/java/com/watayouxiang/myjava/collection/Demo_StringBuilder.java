package com.watayouxiang.myjava.collection;

/*

JDK1.5 版本之后出现了StringBuilder

StringBuffer是线程同步
StringBuilder是线程不同步

以后开发，建议使用StringBuilder

 */
public class Demo_StringBuilder {

    public static void main(String[] args) {
		StringBuilder_test01();
        StringBuilder_test02();
    }

    private static void StringBuilder_test02() {
        int[] arr = new int[]{3, 1, 5, 8, 23, 9};
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        for (int x = 0; x < arr.length; x++) {
            if (x != arr.length - 1)
                builder.append(arr[x] + ", ");
            else
                builder.append(arr[x] + "}");
        }

        System.out.println(builder);
    }

    private static void StringBuilder_test01() {
        StringBuilder builder = new StringBuilder("abcdef");

        // 拷贝 builder 中的 [1, 4] 字符
        // 放到 chs 中，从 1 的位置开始
        char[] chs = new char[6];
        builder.getChars(1, 4, chs, 1);

        for (int x = 0; x < chs.length; x++) {
            System.out.println("chs[" + x + "]=" + chs[x] + ";");
        }
    }

}
