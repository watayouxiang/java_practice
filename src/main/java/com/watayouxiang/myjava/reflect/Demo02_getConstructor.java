package com.watayouxiang.myjava.reflect;

import java.lang.reflect.Constructor;

public class Demo02_getConstructor {

    /**
     * 获取Class中的构造函数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        getConstructor01();
//        getConstructor02();
        other();
    }

    /**
     * 用放射实现等同于new String(new StringBuffer("abc"));效果
     *
     * @throws Exception 异常
     */
    private static void other() throws Exception {
        Constructor<String> constructor = String.class.getConstructor(StringBuffer.class);
        String abc = constructor.newInstance(new StringBuffer("abc"));
        System.out.println(abc);
    }

    private static void getConstructor02() throws Exception {
        // 寻找该名称类文件，并加载进内存，产生Class对象
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");

        // 获取指定的构造函数
        Constructor personConstructor = personClazz.getConstructor(int.class, String.class);
        // 通过该构造器newInstance()方法进行对象的初始化
        Object person = personConstructor.newInstance(18, "wata");
        System.out.println(person);
    }

    private static void getConstructor01() throws Exception {
        // 寻找该名称类文件，并加载进内存，产生Class对象
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");

        // 通过空参数的构造函数进行对象的初始化
        Object person = personClazz.newInstance();
        System.out.println(person);
    }

}
