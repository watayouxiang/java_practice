package com.watayouxiang.myjava.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Demo03_getField {

    /**
     * 获取Class中的字段
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        getPublicField();
//        getPrivateField();
        other();
    }

    /**
     * 修改Person类中的所有String字段的值
     *
     * @throws IllegalAccessException 非法访问异常
     */
    private static void other() throws IllegalAccessException {
        Person person = new Person();
        person.setName("aaabbbccc");
        System.out.println(person);

        Field[] fields = person.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                String oldValue = (String) field.get(person);
                String newValue = oldValue.replace('b', 'B');
                field.set(person, newValue);
            }
        }

        System.out.println(person);
    }

    private static void getPrivateField() throws Exception {
        // 获取Person的字节码
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");
        // 实例化Person对象
        Constructor personConstructor = personClazz.getConstructor(int.class, String.class);
        Object person = personConstructor.newInstance(18, "张三");

        // 获取Person的字段，可以是私有的
        Field nameField = personClazz.getDeclaredField("name");
        // 对私有字段的访问需要取消限制检查（也叫“暴力访问”）
        nameField.setAccessible(true);

        // 获取Person对象的Field值
        Object name = nameField.get(person);
        System.out.println(name);
    }

    private static void getPublicField() throws Exception {
        // 获取Person的字节码
        Class personClazz = Class.forName("com.watayouxiang.android.java.reflect.Person");
        // 实例化Person对象
        Constructor personConstructor = personClazz.getConstructor(int.class, String.class);
        Object person = personConstructor.newInstance(18, "张三");

        // 获取Person的字段，只能获取公有的
        Field ageField = personClazz.getField("age");
        Object age = ageField.get(person);
        System.out.println(age);
    }

}
