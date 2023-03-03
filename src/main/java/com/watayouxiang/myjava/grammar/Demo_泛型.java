package com.watayouxiang.myjava.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*

?通配符。也可以理解为占位符。
 
泛型的限定：

? extends E: 可以接收E类型或者E的子类型。上限。
? super E: 可以接收E类型或者E的父类型。下限

 */
public class Demo_泛型 {

    public static void main(String[] args) {
        List<Animal> al = new ArrayList<Animal>();
        al.add(new Animal("雌性动物"));
        al.add(new Dog("柯基"));
        al.add(new Fish("三文鱼"));

        print_extends(al);
        print_super(al);
    }

    /**
     * ? 通配符，表示接收Dog或者Dog的父类
     *
     * @param collection
     */
    private static void print_super(Collection<? super Dog> collection) {
        Iterator<? super Dog> it = collection.iterator();
        while (it.hasNext()) {
            Object object = it.next();
            System.out.println(object);
        }
    }

    /**
     * ? 通配符，表示接收Animal或者Animal的子类。
     *
     * @param collection
     */
    private static void print_extends(Collection<? extends Animal> collection) {
        Iterator<? extends Animal> it = collection.iterator();
        while (it.hasNext()) {
            Animal animal = it.next();
            System.out.println(animal);
        }
    }

    //==========================================================
    // 内部类
    //==========================================================

    static class Fish extends Animal {
        Fish(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "Fish [name=" + getName() + "]";
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "Dog [name=" + getName() + "]";
        }
    }

    static class Animal {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Animal [name=" + name + "]";
        }
    }

}