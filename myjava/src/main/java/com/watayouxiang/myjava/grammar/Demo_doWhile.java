package com.watayouxiang.myjava.grammar;

public class Demo_doWhile {

    public static void main(String[] args) {
        testDoWhile();
    }

    /**
     * do...while: 先执行循环体，再判断条件是否满足，循环体至少执行一次。
     */
    private static void testDoWhile() {
        int y = 1;
        do {
            System.out.println("do : y=" + y);
            y++;
        } while (y < 3);
    }

}
