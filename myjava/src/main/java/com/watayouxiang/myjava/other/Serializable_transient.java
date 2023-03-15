package com.watayouxiang.myjava.other;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializable_transient {

    public static void main(String[] args) throws Exception {
        writeObj();
        readObj();
    }

    /**
     * 序列化对象
     */
    private static void readObj() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.object"));
        Person person = (Person) ois.readObject();
        System.out.println(person);
    }

    /**
     * 反序列化
     */
    private static void writeObj() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.object"));
        oos.writeObject(new Person("theT", 18, "cn"));
        oos.close();
    }

    static class Person implements Serializable {//没有方法的接口叫做标记接口，类实现了该接口才有执行资格
        public static final long serialVersionUID = 42L;//自定义UID，UID用于给类定义一个固定辨识
        String name;
        /*transient*/ int age;//如果想让非静态变量也不被序列化，那么就加上关键字：transient
        /*static*/ String country;//注意：静态变量是不能被序列化的

        Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

}
