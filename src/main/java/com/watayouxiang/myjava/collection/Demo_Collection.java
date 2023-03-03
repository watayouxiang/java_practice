package com.watayouxiang.myjava.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/*

集合框架中的Collection接口：
	
	Collection是集合框架中的一个顶层接口，它里面定义了单列集合的共性方法。
	
	它有两个常用的子接口:
		|--List：有序的，可重复。
		|--Set：无序，不可重复。

 */
public class Demo_Collection {

    public static void main(String[] args) {
        containsAll();
        retainAll();
        collection2Array();
    }

    /**
     * Collection转数组
     */
    private static void collection2Array() {
        Collection<String> collection = new ArrayList<String>();
        collection.add("abc1");
        collection.add("abc2");
        collection.add("abc3");

        // 將集合转成数组
        String[] arr = collection.toArray(new String[collection.size()]);
        System.out.println("Collection转Array: " + Arrays.toString(arr));
    }

    /**
     * 获取Collection1和Collection2的交集
     */
    private static void retainAll() {
        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        Collection<String> c2 = new ArrayList<String>();
        c2.add("abc2");
        c2.add("abc3");
        System.out.println("c1= " + c1);
        System.out.println("c2= " + c2);

        // 取交集c1和c2的交集，交集放在c1中
        boolean retainAll = c1.retainAll(c2);
        System.out.println("c1 retainAll c2 = " + retainAll);
        System.out.println("交集放在了c1 = " + c1);
    }

    /**
     * 判断Collection是否包含另一个Collection
     */
    private static void containsAll() {
        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        Collection<String> c2 = new ArrayList<String>();
        c2.add("abc2");
        c2.add("abc3");

        // 判断c1中是否包含c2的所有元素。
        boolean containsAll = c1.containsAll(c2);
        System.out.println(c1 + " containsAll " + c2 + ": " + containsAll);
    }

}
