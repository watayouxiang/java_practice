package com.watayouxiang.myjava.grammar;

public class Demo_while {

    public static void main(String[] args) {
        testWhile();
    }

    /**
     * while: 先判断条件，只有条件满足才执行循环体。
     */
    private static void testWhile() {
        int x = 1;
        while (x < 3) {
            System.out.println("while : x=" + x);
            x++;
        }
    }

}
