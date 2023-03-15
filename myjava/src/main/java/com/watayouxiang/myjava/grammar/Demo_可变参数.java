package com.watayouxiang.myjava.grammar;

import java.util.Arrays;

/*

JDK1.5新特性：可变参数
	不用每一次都建立数组对象。
	只要將要操作的元素作为参数传递即可。
	虚拟机会將这些参数封装成了数组。

	public static void show(String str, int... arr) {
		System.out.println(str + ", " + Arrays.toString(arr));
	}
	注：在使用可变参数时，可变参数一定要定义在参数列表的最后面。
	
*/
public class Demo_可变参数 {

	public static void main(String[] args) {
		show("哈哈", 0, 1, 2, 3, 4);
		show("哈哈", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	}

	private static void show(String str, int... arr) {
		System.out.println(str + ", " + Arrays.toString(arr));
	}

}
