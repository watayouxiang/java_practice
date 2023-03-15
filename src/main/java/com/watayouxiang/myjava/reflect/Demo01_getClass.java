package com.watayouxiang.myjava.reflect;

import java.lang.reflect.Array;

public class Demo01_getClass {

    /**
     * 获取字节码的三种方式
     *
     * @param args
     */
    public static void main(String[] args) {
//        getClass01();
//        getClass02();
//        getClass03();
        other();
    }

    /**
     * Class.isPrimitive()
     * Class.isArray()
     * Integer.TYPE
     */
    private static void other() {
        // 判断是否Class是否为基本数据类型
        System.out.println("String是否是基本数据类型：" + String.class.isPrimitive());
        System.out.println("int是否是基本数据类型：" + int.class.isPrimitive());
        System.out.println("int[]是否是基本数据类型：" + int[].class.isPrimitive());
        System.out.println("int[]是否是\"数组数据类型\"：" + int[].class.isArray());
        //int和Integer是否相同
        System.out.println("int.class == Integer.class：" + (int.class == Integer.class));
        System.out.println("int.class == Integer.TYPE：" + (int.class == Integer.TYPE));
        //打印父类名字
        System.out.println("int[]的父类名字：" + (int[].class.getSuperclass().getName()));
        System.out.println("int[][]的父类名字：" + (int[][].class.getSuperclass().getName()));
        //打印
        printObject("123");
        printObject(new String[]{"a", "b", "c"});
        printObject(new String[][]{{"11", "12"}, {"21", "22"}});
    }

    /**
     * 打印对象中的所有元素
     *
     * @param obj 对象
     */
    private static void printObject(Object obj) {
        Class<?> aClass = obj.getClass();
        if (aClass.isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object o = Array.get(obj, i);
                printObject(o);
            }
        } else {
            System.out.println(obj);
        }
    }

    private static void getClass03() {
        // 3. 通过给定类的字符串名称获取（最常用）
        try {
            Class aClass = Class.forName("com.watayouxiang.android.java.reflect.Person");
            System.out.println(aClass.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getClass02() {
        // 2. 任何数据类型都具有一个静态属性.class来获取对应的Class对象
        Class aClass = Person.class;
        System.out.println(aClass.toString());
    }

    private static void getClass01() {
        // 1. Object类中的getClass()方法
        Person person = new Person();
        Class aClass = person.getClass();
        System.out.println(aClass.toString());
    }

}
