package com.watayouxiang.myjava.thread.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：CopyOnWriteArrayList可以在迭代的过程中修改数组内容，但是ArrayList不行
 */
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        System.out.println(list);
        Iterator<Integer> iterator1 = list.iterator();

        list.add(4);
        System.out.println(list);

        Iterator<Integer> iterator2 = list.iterator();

        iterator1.forEachRemaining(System.out::print);
        System.out.println();
        iterator2.forEachRemaining(System.out::print);

    }
}
