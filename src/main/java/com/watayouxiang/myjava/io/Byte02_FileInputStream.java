package com.watayouxiang.myjava.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*

InputStream
	--| FileInputStream 文件输入字节流

 */
public class Byte02_FileInputStream {

	public static void main(String[] args) throws IOException {
		test_readOneByte();
		test_readByteArr();
		test_readAvailableByteArr();

	}

	private static void test_readAvailableByteArr() throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			System.out.println("file not exists");
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		int available = fis.available();
		System.out.println("available = " + available);

		int len = 0;
		byte[] buf = new byte[available];// 定义一个刚刚好的缓冲区
		while ((len = fis.read(buf)) != -1) {
			String string = new String(buf, 0, len);
			System.out.print(string);
		}
		fis.close();
	}

	private static void test_readByteArr() throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			System.out.println("file not exists");
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
			String string = new String(buf, 0, len);
			System.out.print(string);
		}
		fis.close();
	}

	private static void test_readOneByte() throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			System.out.println("file not exists");
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		int ch = 0;
		while ((ch = fis.read()) != -1) {
			System.out.print((char) ch);
		}
		fis.close();
	}

}
