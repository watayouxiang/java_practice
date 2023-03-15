package com.watayouxiang.myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
io包的ObjectOutputStream类，ObjectInputStream类：
用于將对象存储存储到文件中去。

被操作的对象需要实现Serializable(标记接口)
 */
public class Byte11_ObjectStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeObj();
        readObj();
    }

    private static void readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.object"));
        Person person = (Person) ois.readObject();
        System.out.println(person);
    }

    private static void writeObj() throws IOException {
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
