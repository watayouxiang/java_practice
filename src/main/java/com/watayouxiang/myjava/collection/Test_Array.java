package com.watayouxiang.myjava.collection;

import java.util.Arrays;

/**
 * {3, 6, 5, 1, 8, 9, 67}
 * <p>
 * 1. 反转数组
 * 2. 冒泡排序
 * 3. 选择排序
 * 4. 折半查找
 * <p>
 * 八种排序算法原理及Java实现: https://juejin.im/post/5b95da8a5188255c775d8124
 * 时间复杂度、空间复杂度: https://zhuanlan.zhihu.com/p/50479555
 */
public class Test_Array {

    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 1, 8, 9, 67};
        System.out.println("原始数组：" + Arrays.toString(arr));

        int[] reverseArray = reverseArray(arr);
        System.out.println("反转后的数组：" + Arrays.toString(reverseArray));

        int[] bubbleArray = bubbleSort(arr);
        System.out.println("冒泡排序后的数组：" + Arrays.toString(bubbleArray));

        int[] selectArray = selectSort(arr);
        System.out.println("选择排序后的数组：" + Arrays.toString(selectArray));
    }

    /**
     * 选择排序
     *
     * @param inArr 排序前的数组
     * @return 排序后的数组
     */
    private static int[] selectSort(int[] inArr) {
        // 时间复杂度 T(n) = O(nLogN)
        // 空间复杂度 S(n) = O(1)

        int[] arr = new int[inArr.length];
        System.arraycopy(inArr, 0, arr, 0, inArr.length);

        // 小的数据放前面
        // arr[0] & arr[1], arr[0] & arr[2], ... , arr[0] & arr[length-1]
        // arr[1] & arr[2], arr[1] & arr[3], ... , arr[1] & arr[length-1]
        // ...
        // arr[length-2] & arr[length-1]

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1 + i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

        return arr;
    }

    /**
     * 冒泡排序
     *
     * @param inArr 排序前数组
     * @return 排序后的数组
     */
    private static int[] bubbleSort(int[] inArr) {
        // 时间复杂度 T(n) = O(nLogN)
        // 空间复杂度 S(n) = O(1)

        int[] arr = arrayCopy(inArr);

        // 大的数据放后面
        // arr[0] & arr[1], arr[1] & arr[2], ... , arr[length-2] & arr[length-1]
        // arr[0] & arr[1], arr[1] & arr[2], ... , arr[length-3] & arr[length-2]
        // ...
        // arr[0] & arr[1]

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }

    /**
     * 反转数组
     *
     * @param inArr 数组
     * @return 反转后的数组
     */
    private static int[] reverseArray(int[] inArr) {
        // 时间复杂度 T(n) = 1 + n/2 = O(n)
        // 空间复杂度 S(n) = O(1)

        int[] arr = arrayCopy(inArr);

        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            swap(arr, start, end);
        }

        return arr;
    }

    /**
     * 拷贝数组
     *
     * @param arr 数组
     * @return 拷贝的数组
     */
    private static int[] arrayCopy(int[] arr) {
        // 时间复杂度 O(1)
        // 空间复杂度 O(1)

        int[] outArr = new int[arr.length];
        System.arraycopy(arr, 0, outArr, 0, arr.length);
        return outArr;
    }

    /**
     * 交换数组中的元素
     *
     * @param arr 数组
     * @param p1  元素1位置
     * @param p2  元素2位置
     */
    private static void swap(int[] arr, int p1, int p2) {
        // 时间复杂度 O(1)
        // 空间复杂度 O(1)

        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
