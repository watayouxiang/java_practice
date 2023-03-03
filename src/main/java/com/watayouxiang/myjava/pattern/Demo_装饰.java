package com.watayouxiang.myjava.pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*

装饰设计模式：
当想要对已有的对象进行功能增强时，可以定义类，将已有的对象传入，
基于已有的功能，并提供增强功能。那么自定义该类称为装饰类。

装饰模式和继承体系的区别：
1）装饰模式比继承要灵活，避免继承体系臃肿。而且降低了类与类之间的关系。
2）装饰类因为增强已有的对象，具备的功能和已有的是相同的，只不过提供了更强功能。
    所以装饰类和被装饰类都是属于一个体系中的。

 */
public class Demo_装饰 {

    public static void main(String[] args) throws IOException {
        apiDemo();
        myDemo();
    }

    private static void myDemo() {
        //普通
        NormalPerson normalPerson = new NormalPerson();
        normalPerson.eat();
        //装饰后
        SuperPerson superPerson = new SuperPerson(normalPerson);
        superPerson.superEat();
    }

    private static void apiDemo() throws IOException {
        //普通
        FileReader fileReader = new FileReader("test.txt");
        int ch = fileReader.read();
        //装饰后
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
    }

    //==============================================================================================

    static abstract class Person {
        abstract void eat();
    }

    /**
     * 普通类
     */
    static class NormalPerson extends Person {
        @Override
        void eat() {
            System.out.println("-- 吃饭 --");
        }
    }

    /**
     * 装饰类
     */
    static class SuperPerson extends Person {
        private Person p;

        SuperPerson(Person p) {
            this.p = p;
        }

        void superEat() {
            System.out.println("~~ 甜点 ~~");
            p.eat();
            System.out.println("~~ 喝酒 ~~");
        }

        @Override
        void eat() {
            p.eat();
        }
    }

}