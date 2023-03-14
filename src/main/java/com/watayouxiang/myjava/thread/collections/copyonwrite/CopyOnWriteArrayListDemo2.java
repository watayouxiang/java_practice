package com.watayouxiang.myjava.thread.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/14
 * description：
 * 1、CopyOnWriteArrayList不能保证数据的一致性，如果希望写入的数据，马上能读到，那么请不要用这个类
 * 2、因为CopyOnWriteArrayList是写复制机制，所以在进行写操作时，内存会同时驻村两个对象的内存
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
