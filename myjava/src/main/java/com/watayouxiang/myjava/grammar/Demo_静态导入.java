package com.watayouxiang.myjava.grammar;

import java.util.Arrays;

import static java.util.Arrays.sort;

/*

JDK1.5新特性：

	import static 静态导入

*/
public class Demo_静态导入 {

	public static void main(String[] args) {
//		before_staticImport();
		after_staticImport();
	}

	/**
	 * 静态导入后的写法
	 */
	private static void after_staticImport() {
		int[] arr = { 3, 1, 5 };
		// import static java.util.Arrays.sort;
		// 静态导入后，“Arrays.sort(arr);” 可以简写成 “sort(arr);”
		sort(arr);
		// 这里的Arrays.toString不能省略，因为Object中也有toString方法。
		// 注意：当方法重名时，需要指定具体的对象或者类。
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 静态导入前的写法
	 */
	private static void before_staticImport() {
		int[] arr = { 3, 1, 5 };
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
