package com.watayouxiang.myjava.collection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test_List {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> list = Arrays.asList("abcd", "aaa", "zz", "kkkkk", "qq", "z");
        String key = "aaa";

        System.out.println("原始列表 " + list);

        List<String> list1 = deepCopy(list);
        bubbleSort(list1);
        System.out.println("冒泡排序 " + list1);

        List<String> list2 = deepCopy(list);
        selectSort(list2);
        System.out.println("选择排序 " + list2);

        int index1 = myBinarySearch(list1, key);
        System.out.println("关键字 " + key + " position = " + index1);

    }

    /**
     * 二分查找
     *
     * @param list 列表
     * @param key  关键字
     * @return 关键字所在的位置
     */
    private static int myBinarySearch(List<String> list, String key) {
        int min = 0;
        int max = list.size() - 1;
        int mid;
        int index = -1;
        while (min <= max) {
            mid = (min + max) >> 1;
            int compare = comparator.compare(key, list.get(mid));
            if (compare > 0) {
                min = mid + 1;
            } else if (compare < 0) {
                max = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    /**
     * 选择排序
     *
     * @param list 列表
     */
    private static void selectSort(List<String> list) {
        // 小的放前面
        // list(0) & list(1), list(0) & list(2), ... , list(0) & list(size-1)
        // list(1) & list(2), list(1) & list(3), ... , list(1) & list(size-1)
        // ...
        // list(size-2) & list(size-1)

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1 + i; j < list.size(); j++) {
                if (comparator.compare(list.get(i), list.get(j)) > 0) {
                    Collections.swap(list, i, j);
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param list 列表
     */
    private static void bubbleSort(List<String> list) {
        // 大的放后面
        // list(0) & list(1), list(1) & list(2), ..., list(size-2) & list(size-1)
        // list(0) & list(1), list(1) & list(2), ..., list(size-3) & list(size-2)
        // ...
        // list(0) & list(1)

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * 列表深拷贝
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 拷贝的数据
     * @throws IOException            异常
     * @throws ClassNotFoundException 异常
     */
    private static <T> List<T> deepCopy(List<T> list) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
        objectOutput.writeObject(list);

        ByteArrayInputStream byteInput = new ByteArrayInputStream(byteOutput.toByteArray());
        ObjectInputStream objectInput = new ObjectInputStream(byteInput);
        @SuppressWarnings("unchecked")
        List<T> object = (List<T>) objectInput.readObject();
        return object;
    }

    private static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int num = o1.length() - o2.length();
            if (num != 0) return num;
            return o1.compareTo(o2);
        }
    };

}
