package com.watayouxiang.myjava.collection;

import java.util.Arrays;
import java.util.List;

public class Demo_Arrays {

    public static void main(String[] args) {
        printArray();
        array2List();
        array2List_UnsupportedOperationException();
        intArray_IntegerArray();
    }

    private static void intArray_IntegerArray() {
        System.out.println("如果数组中的元素都是基本数据类型，那么会將该数组作为集合中的元素存在");
        int[] arr = {2, 4, 5};
        List<int[]> list = Arrays.asList(arr);
        System.out.println(list);

        System.out.println("如果数组中的元素都是对象，那么变成集合时，数组中的元素就直接转成集合中的元素");
        Integer[] arr2 = {2, 4, 5};
        List<Integer> list2 = Arrays.asList(arr2);
        System.out.println(list2);
    }

    private static void array2List_UnsupportedOperationException() {
        String[] arr = {"abc", "cc", "kkkk"};
        List<String> list = Arrays.asList(arr);

        try {
            System.out.println("將数组变成集合，不可以使用集合的增删方法，因为数组的长度是固定的");
            System.out.println("如果你增删了，会发生UnsupportedOperationException异常");
            list.add("qq");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    private static void array2List() {
        // 数组转List
        String[] arr = {"abc", "cc", "kkkk"};
        List<String> list = Arrays.asList(arr);
        System.out.println("Array转List：" + list);
    }

    private static void printArray() {
        // 打印数组内容
        int[] arr = {2, 4, 5};
        System.out.println("打印数组内容：" + Arrays.toString(arr));
    }

}
