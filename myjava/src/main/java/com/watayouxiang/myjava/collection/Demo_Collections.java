package com.watayouxiang.myjava.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*

集合框架的Collections工具类：
	Collections是集合框架中的一个工具类，该类中的方法都是静态的。
	提供的方法中有可以对list集合进行排序，二分查找等方法。

 */
public class Demo_Collections {

    public static void main(String[] args) {
        sort();
        swap();
        max();
        myBinarySearch();
        binarySearch();
        reverseOrder();
        shuffle();
        synchronizedCollection();
    }

    private static void synchronizedCollection() {
        List<String> syncList = Collections.synchronizedList(new ArrayList<String>());
        Map<String, Boolean> syncMap = Collections.synchronizedMap(new HashMap<String, Boolean>());
        Set<String> syncSet = Collections.synchronizedSet(new TreeSet<String>());

        ArrayList<String> syncCollection = (ArrayList<String>) Collections.synchronizedCollection(new ArrayList<String>());
    }

    private static void shuffle() {
        System.out.println("--- shuffle ---");

        // 获取数据
        List<String> list = getList();
        System.out.println(list);

        // 將list中的元素按随机顺序进行排序
        Collections.shuffle(list);
        System.out.println(list);
        System.out.println();
    }

    private static void reverseOrder() {
        System.out.println("--- reverseOrder ---");

        // 获取数据
        List<String> list = getList();

        // 先排序
        Collections.sort(list, comparator);
        System.out.println("排序: " + list);

        // 反转排序
        Comparator<String> reverseComparator = Collections.reverseOrder(comparator);
        Collections.sort(list, reverseComparator);
        System.out.println("反转排序: " + list);
        System.out.println();
    }

    private static void binarySearch() {
        System.out.println("--- binarySearch ---");

        // 获取数据
        String key = "qq";
        List<String> list = getList();

        // 先排序
        Collections.sort(list, comparator);
        System.out.println("自定义排序: " + list);

        int index = Collections.binarySearch(list, key, comparator);
        System.out.println(key + " index = " + index);
        System.out.println();
    }

    private static void myBinarySearch() {
        System.out.println("--- myBinarySearch ---");

        // 获取数据
        String key = "qq";
        List<String> list = getList();

        // 先排序
        Collections.sort(list, comparator);
        System.out.println("自定义排序: " + list);

        // 二分查找法查询
        int min = 0;
        int max = list.size() - 1;
        int mid;
        int index = -1;
        while (min <= max) {
            mid = (max + min) >> 1;
            int num = comparator.compare(key, list.get(mid));
            if (num > 0) {
                min = mid + 1;
            } else if (num < 0) {
                max = mid - 1;
            } else {
                index = mid;
                break;
            }
        }

        System.out.println(key + " index = " + index);
        System.out.println();
    }

    private static void max() {
        System.out.println("--- max ---");

        List<String> list = getList();
        System.out.println("列表数据 " + list);

        String max = Collections.max(list);
        System.out.println("默认排序的最大值max = " + max);

        String max2 = Collections.max(list, comparator);
        System.out.println("自定义排序的最大值max = " + max2);
        System.out.println();
    }

    private static void swap() {
        System.out.println("--- swap ---");

        List<String> list = getList();
        System.out.println("交互位置前 " + list);

        Collections.swap(list, 0, 1);
        System.out.println("交互0,1位置后 " + list);
        System.out.println();
    }

    private static void sort() {
        System.out.println("--- sort ---");

        List<String> list = getList();
        System.out.println("排序前 " + list);

        Collections.sort(list);
        System.out.println("自然顺序排序 " + list);

        Collections.sort(list, comparator);
        System.out.println("自定义排序 " + list);
        System.out.println();
    }

    // =====================================================================================
    // 其他方法
    // =====================================================================================

    private static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int num = o1.length() - o2.length();
            if (num != 0) return num;
            return o1.compareTo(o2);
        }
    };

    private static List<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("zz");
        list.add("kkkkk");
        list.add("qq");
        list.add("z");
        return list;
    }

}