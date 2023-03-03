package com.watayouxiang.myjava.java7;

public class Java7_MutilCatch {
    public static void main(String[] args) {
        int arr[] = new int[3];

        //老版本写法
        try {
            int element = getElement(arr, 1);
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

        //新版本写法
        try {
            int element = getElement(arr, 1);
        } catch (final/* 这里又一个隐式final */ NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }
    }

    private static int getElement(int[] arr, int index) throws NullPointerException, ArrayIndexOutOfBoundsException {
        if (arr == null) {
            throw new NullPointerException("数组不存在");
        }
        if (index < 0 || index >= arr.length) {
            throw new ArrayIndexOutOfBoundsException("数组不存在");
        }
        return arr[index];
    }
}
