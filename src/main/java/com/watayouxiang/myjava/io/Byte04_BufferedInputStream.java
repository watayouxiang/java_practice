package com.watayouxiang.myjava.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*

InputStream
	--| BufferedInputStream 带缓冲区的输入字节流（只要用到缓冲区，就要记得刷新）

 */
public class Byte04_BufferedInputStream {

	public static void main(String[] args) throws IOException {
		readOneByte();
		readByteArr();

	}

	private static void readByteArr() throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			System.out.println("file not exist");
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = bis.read(buf)) != -1) {
			String string = new String(buf, 0, len);
			System.out.print(string);
		}
		bis.close();
	}

	private static void readOneByte() throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			System.out.println("file not exist");
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int ch = 0;
		while ((ch = bis.read()) != -1) {
			System.out.print((char) ch);
		}
		bis.close();
	}

}
