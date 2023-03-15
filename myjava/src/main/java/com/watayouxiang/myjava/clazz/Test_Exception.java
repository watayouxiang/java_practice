package com.watayouxiang.myjava.clazz;

public class Test_Exception {

    public static void main(String[] args) {
        int value = testException();
        System.out.println(value);
    }

    /**
     * finally肯定会被执行
     *
     * @return 2
     */
    private static int testException() {
        try {
            return 5 / 0;
        } catch (ArithmeticException e) {
            return 3;
        } finally {
            return 2;
        }
    }

}
