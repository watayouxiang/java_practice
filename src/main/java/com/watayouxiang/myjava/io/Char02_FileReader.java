package com.watayouxiang.myjava.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*

Reader
--| FileReader 读字符流

 */
public class Char02_FileReader {

	public static void main(String[] args) {
		readOneChar();
		readCharArr();
	}

	private static void readCharArr() {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			System.out.println("文件不存在");
			return;
		}
		// 要保证该文件是已经存在的，如果不存在，会发生异常FileNotFoundException
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			char[] buf = new char[1024];
			int len = 0;
			// read(char[])返回的是读到字符个数
			while ((len = fileReader.read(buf)) != -1) {
				System.out.print(new String(buf, 0, len));
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readOneChar() {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			System.out.println("文件不存在");
			return;
		}

		// 要保证该文件是已经存在的，如果不存在，会发生异常FileNotFoundException
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			while (true) {
				// 读取单个字符。如果已到达流的末尾，则返回 -1
				int ch = fileReader.read();
				if (ch == -1)
					break;
				System.out.print((char) ch);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
