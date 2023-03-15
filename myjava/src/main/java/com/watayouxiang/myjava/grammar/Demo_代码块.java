package com.watayouxiang.myjava.grammar;

public class Demo_代码块 {

	public static void main(String[] args) {
		/*
		 * 该句话都做了什么事情？ 
		 * 1，因为new用到了Person.class.所以会先找到Person.class文件并加载到内存中。
		 * 2，执行该类中的static代码块，给Person.class类进行初始化。 
		 * 3，在堆内存中开辟空间，分配内存地址。
		 * 4，在堆内存中建立对象的特有属性。并进行默认初始化。 
		 * 5，对属性进行显示初始化。 
		 * 6，对对象进行构造代码块初始化。 
		 * 7，对对象进行对应的构造函数初始化。
		 * 8，将内存地址赋值给栈内存中的p变量。
		 */
		Person p = new Person();
	}

	//==========================================================
	// 内部类
	//==========================================================

	static class Person {
		static {
			/*
			 * 静态代码块
			 *
			 * 作用：给类进行初始化的。 何时运行：随着类的加载而执行，只执行一次，并优先于主函数。
			 */
			System.out.println("静态代码块");
		}

		{
			/*
			 * 构造代码块
			 *
			 * 作用：给所有对象进行初始化 何时运行：对象一建立就运行，而且优先于构造函数执行。
			 */
			System.out.println("构造代码块");
		}

		Person() {
			System.out.println("构造函数");
		}
	}
}