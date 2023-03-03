package com.watayouxiang.myjava.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class Char08_Test {

	public static void main(String[] args) throws IOException {
		modify_systemIn_systemOut();
		copy_File();

	}

	/**
	 * 更改 System.in 和 System.out，拷贝文件
	 * 
	 * @throws IOException
	 */
	private static void copy_File() throws IOException {
		File fromFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/Char08_Test.java");
		File toFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/CharDemo08_test_复制.java");

		// 设置源: 原本是键盘，现在改成文件
		System.setIn(new FileInputStream(fromFile));
		// 设置目的: 原本是控制台，现在改成文件
		System.setOut(new PrintStream(toFile));

		// 键盘的最常见写法。
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		}

		bufferedReader.close();
		bufferedWriter.close();
	}

	/**
	 * 通过键盘录入数据。当录入一行数据后，就将该行数据进行打印大写。如果录入的数据是over，那么停止录入。
	 * 
	 * @throws IOException
	 */
	private static void modify_systemIn_systemOut() throws IOException {
		// 字节输入流 转 字符读入流
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// 字节输出流 转 字符写出流
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if ("over".equals(line)) {
				bufferedWriter.write("-- END --");
				break;
			}
			bufferedWriter.write("大写: " + line.toUpperCase());
			bufferedWriter.newLine();
			// 写出去后要flush刷新才能显示
			bufferedWriter.flush();
		}

		bufferedReader.close();
		bufferedWriter.close();
	}

}
