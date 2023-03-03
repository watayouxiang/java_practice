package com.watayouxiang.myjava.grammar;

public class Demo_for {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        testContinue();
        testBreak();
    }

    /**
     * break：跳出并结束整个循环
     */
    private static void testBreak() {
        w:for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                System.out.println("x=" + x);
                break w;// w指break作用于外循环
            }
        }
    }

    /**
     * continue: 结束本次循环，继续下一次循环
     */
    private static void testContinue() {
        w:for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                System.out.println("x=" + x);
                continue w;// w指continue作用于外循环
            }
        }
    }

    /**
     * ----*
     * ---* *
     * --* * *
     * -* * * *
     * * * * * *
     * <p>
     * * * * * *
     * -* * * *
     * --* * *
     * ---* *
     * ----*
     */
    private static void test05() {
        for (int x = 0; x < 5; x++) {
            for (int y = x; y < 4; y++) {
                System.out.print("-");
            }
            for (int z = 0; z <= x; z++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < x; y++) {
                System.out.print("-");
            }
            for (int z = x; z < 5; z++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    /**
     * 九九乘法表
     * <p>
     * 1*1=1
     * 1*2=2   2*2=4
     * 1*3=3   2*3=6   3*3=9
     * 1*4=4   2*4=8   3*4=12  4*4=16
     * 1*5=5   2*5=10  3*5=15  4*5=20  5*5=25
     */
    private static void test04() {
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y <= x; y++) {
                System.out.print(y + "*" + x + "=" + (x * y) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * ****
     * ***
     * **
     * *
     */
    private static void test03() {
        for (int x = 0; x < 5; x++) {
            for (int y = x; y < 5; y++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 求出1～100之间，即使3又是7的倍数出现的次数？
     */
    private static void test02() {
        int count = 0;
        for (int x = 1; x <= 100; x++) {
            if (x % 3 == 0 && x % 7 == 0) {
                count++;
            }
        }
        System.out.println("count = " + count);
    }

    /**
     * 练习：3000米长的绳子，每天减一半。
     * 问多少天这个绳子会小于5米？不考虑小数。
     */
    private static void test01() {
        int day = 0;
        for (int x = 3000; x >= 5; x /= 2) {
            day++;
        }
        System.out.println("day = " + day);
    }

}
