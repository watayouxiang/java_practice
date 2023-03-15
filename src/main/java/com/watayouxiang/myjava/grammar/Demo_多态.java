package com.watayouxiang.myjava.grammar;

/*
多态
1. 多态的体现: 父类的引用指向子类对象。
2. 多态的前提:
    1) 必须是类与类之间有关系。要么继承，要么实现。
    2) 通常还有一个前提：存在覆盖。
3. 多态的好处: 多态的出现大大的提高程序的扩展性。
4. 多态的弊端：虽然提高了扩展性，但是只能使用父类的引用访问父类中的成员。
5. 在多态中，变量的特点：无论编译和运行，都参考父类。
6. 在多态中，函数的特点：
    1) 在编译时期：参考父类中是否有调用的方法。如果有，编译通过，如果没有编译失败。
    2) 在运行时期：参考子类中是否有调用的方法。
    3) 简单总结就是：成员函数在多态调用时，编译看父类，运行看子类。但是，如果是静态函数，那么无论编译还是运行都参考父类
 */
public class Demo_多态 {

    public static void main(String[] args) {
        Parent p = new Child();

        // 编译运行都看父类
        System.out.println(p.normal);
        // 编译看父类，运行看子类（多态的体现）
        p.method2();

        // 静态变量，编译运行都看父类
        System.out.println(p.num);
        // 静态函数，编译运行都看父类
        p.method3();
    }

    //==========================================================
    // 内部类
    //==========================================================

    static class Child extends Parent {
        static int num = 8;
        int normal = 1008;

        void method1() {
            System.out.println("zi method_1");
        }

        void method2() {
            System.out.println("zi method_2");
        }

        static void method3() {
            System.out.println("zi method_3");
        }
    }

    static class Parent {
        static int num = 5;
        int normal = 1005;

        void method1() {
            System.out.println("fu method_1");
        }

        void method2() {
            System.out.println("fu method_2");
        }

        static void method3() {
            System.out.println("fu method_3");
        }
    }
}