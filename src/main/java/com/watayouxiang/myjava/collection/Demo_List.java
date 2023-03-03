package com.watayouxiang.myjava.collection;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/*

List：元素是有序的，元素可以重复。因为该集合体系有索引。

	|--ArrayList: 数组数据结构。特点：查询快，增删慢，线程不同步。
	|--LinkedList: 链表数据结构。特点：增删快，查询慢，线程不同步。
	|--Vector: 数组数据结构。线程同步。被ArrayList替代了。因为效率低。

    Iterator：公有迭代器。Iterator在迭代时，不可进行增删操作，否则会发生ConcurrentModificationException异常。
    ListIterator：List集合特有的迭代器，是Iterator的子接口，可以在迭代时进行增删操作。
    Enumeration：Vector集合特有的迭代器

 */
public class Demo_List {

    public static void main(String[] args) {
        concurrentModificationException();
        iteratorList();
        getVectorElements();
        test_LinkedList();
    }

    private static void test_LinkedList() {
        System.out.println("--- LinkedList iterator ---");

        LinkedList<String> list = new LinkedList<String>();
        list.addLast("java");
        list.addLast("java");
        list.addLast("C#");

        while (!list.isEmpty()) {
            String last = list.removeLast();
            System.out.println(last);
        }
        System.out.println();
    }

    private static void getVectorElements() {
        System.out.println("--- Vector iterator ---");

        Vector<String> vector = new Vector<String>();
        vector.add("java");
        vector.add("java");
        vector.add("C#");

        // 枚举Enumeration，是Vector特有的
        Enumeration<String> en = vector.elements();
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
        System.out.println();
    }

    private static void iteratorList() {
        System.out.println("--- List iterator ---");

        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("Android");
        list.add("C#");

        // 迭代器ListIterator，是List类特有的
        ListIterator<String> listIt = list.listIterator();
        while (listIt.hasNext()) {
            String next = listIt.next();
            if ("Android".equals(next)) {
                listIt.remove();
            }
            if ("java".equals(next)) {
                listIt.set("java super");
            }
        }
        System.out.println(list);
        System.out.println();
    }

    private static void concurrentModificationException() {
        System.out.println("--- ConcurrentModificationException ---");

        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("Android");
        list.add("C#");

        // 迭代器Iterator，是Collection类公有的
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                String next = it.next();
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
                break;
            }
            // 在迭代器迭代时，增删集合会造成ConcurrentModificationException异常
            list.add("java02");
            list.remove(0);
        }

        System.out.println();
    }

}
