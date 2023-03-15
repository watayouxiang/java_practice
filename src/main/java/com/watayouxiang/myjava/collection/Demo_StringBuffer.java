package com.watayouxiang.myjava.collection;

/*

JDK1.5 版本之后出现了StringBuilder

StringBuffer是线程同步
StringBuilder是线程不同步

以后开发，建议使用StringBuilder

 */
public class Demo_StringBuffer {

    public static void main(String[] args) {
		StringBuffer_add();
		StringBuffer_del();
        StringBuffer_update();
    }

    private static void StringBuffer_update() {
        StringBuffer sb = new StringBuffer("abcde");

        sb.replace(1, 4, "java");
        System.out.println(sb.toString());// ajavae

        sb.setCharAt(2, 'k');
        System.out.println(sb.toString());// ajkvae
    }

    private static void StringBuffer_del() {
        StringBuffer buffer = new StringBuffer("abcde");

        buffer.delete(1, 3);
        System.out.println(buffer.toString());// ade

        buffer.deleteCharAt(2);
        System.out.println(buffer.toString());// ad
    }

    private static void StringBuffer_add() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("abc").append('d').append(true).append(34);
        System.out.println(buffer.toString());

        buffer.insert(1, "qq");
        System.out.println(buffer);// abcdtrue34
    }
}
