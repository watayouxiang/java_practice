package com.watayouxiang.myjava.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Demo04_getMethods {

    /**
     * 获取Class中的方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        getPublicMethods();
        getPrivateMethods();
        invokePublicMethod();
        invokePrivateMethod();
    }

    private static void invokePrivateMethod() throws Exception {
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");
        Constructor personConstructor = personClazz.getConstructor(int.class, String.class);
        Object person = personConstructor.newInstance(18, "张三");

        // 调用有参私有方法
        Method sayMethod = personClazz.getDeclaredMethod("say", String.class);
        // 取消访问限制，又叫“暴力访问”
        sayMethod.setAccessible(true);
        sayMethod.invoke(person, "新年快乐~");
    }

    private static void invokePublicMethod() throws Exception {
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");
        Constructor personConstructor = personClazz.getConstructor(int.class, String.class);
        Object person = personConstructor.newInstance(18, "张三");
        System.out.println(person);

        // 调用无参公有方法
        Method getNameMethod = personClazz.getMethod("getName", null);
        Object name = getNameMethod.invoke(person, null);
        System.out.println("name = " + name);

        // 调用有参公有方法
        Method setNameMethod = personClazz.getMethod("setName", String.class);
        setNameMethod.invoke(person, "李四");
        System.out.println(person);
    }

    private static void getPrivateMethods() throws Exception {
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");

        // 只获取本类中所有的方法，包含私有方法
        Method[] declaredMethods = personClazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    private static void getPublicMethods() throws Exception {
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");

        // 获取所有的公有的方法
        Method[] methods = personClazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

}
