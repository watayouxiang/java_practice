package com.watayouxiang.myjava.clazz;

import java.util.Random;

public class Demo_Math {

	public static void main(String[] args) {
		System.out.println("---------- ceil返回大于指定数据的最小整数 ----------");
		double num = Math.ceil(16.34);
		System.out.println(num);

		System.out.println("---------- floor返回小于指定数据的最大整数 ----------");
		double num2 = Math.floor(12.34);
		System.out.println(num2);

		System.out.println("---------- 四舍五入 ----------");
		long num3 = Math.round(12.54);
		System.out.println(num3);

		System.out.println("---------- 2的3次方 ----------");
		double num4 = Math.pow(2, 3);
		System.out.println(num4);

		System.out.println("---------- 随机数 ----------");
		for (int i = 0; i < 10; i++) {
			double random = Math.random();// [0, 1)
			int random2 = (int) (random * 10 + 1);// [1, 10]
			System.out.println(random2);
		}

		System.out.println("---------- 随机数2 ----------");
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int nextInt = random.nextInt(10);// [0, 10)
			System.out.println(nextInt);
		}

	}

}
