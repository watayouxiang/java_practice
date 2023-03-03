package com.watayouxiang.myjava.jdk10;

import java.util.ArrayList;
import java.util.List;

//不可变集合
public class Sample04 {
    public static void main(String[] args) {
        final var list = new ArrayList<String>();
        list.add("ABC");
        list.add("BCD");
        list.add("EFG");
        //List.copyOf创建不可变集合
        List<String> copyList = List.copyOf(list);
        copyList.add("A");

    }
}
