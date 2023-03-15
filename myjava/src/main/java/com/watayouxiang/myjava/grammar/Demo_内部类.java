package com.watayouxiang.myjava.grammar;

/*

 内部类：

	 1，内部类可以直接访问外部类中的成员，包括私有。之所以可以直接访问外部类中的成员，
	 	是因为内部类中持有了一个外部类的引用，格式：外部类名.this
	 
	 2，内部类定义在局部时：
		 1）不可以被成员修饰符修饰
		 2）可以直接访问外部类中的成员，因为还持有外部类中的引用。
		 	但是不可以访问它所在的局部中的变量。只能访问被final修饰的局部变量。
		 	
	 注意：当内部类中定义了静态成员，该内部类必须是static的。
			当外部类中的静态方法访问内部类时，内部类也必须是static的。
 
 匿名内部类：
 	
 	 1，匿名内部类其实就是内部类的简写格式。
	 2，定义匿名内部类的前提：内部类必须是继承一个类或者实现接口。
	 3，匿名内部类的格式：new 父类或者接口(){定义子类的内容}
	 4，其实匿名内部类就是一个匿名子类对象。而且这个对象有点胖。可以理解为带内容的对象。

 */
public class Demo_内部类 {

    public static void main(String[] args) {
        // 内部类，方法
        new Outer().new Inner().method();
        // 静态内部类，方法
        new Outer.StaticInner().method();
        // 静态内部类，静态方法
        Outer.StaticInner.staticMethod();

        // 匿名内部类
        new TestInf() {
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        }.show();
    }

    //==========================================================
    // 内部类
    //==========================================================

    interface TestInf {
        void show();
    }

    static class Outer {
        class Inner {
            void method() {
                System.out.println("Inner method");
            }
        }

        static class StaticInner {
            void method() {
                System.out.println("StaticInner method");
            }

            static void staticMethod() {
                System.out.println("StaticInner static method");
            }
        }
    }

}