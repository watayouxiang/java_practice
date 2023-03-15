package com.watayouxiang.myjava.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Char05_Test {

	public static void main(String[] args) throws IOException {
		test_FileWriter_FileReader_copyJavaFile();
		test_BufferedWriter_BufferedReader_copyJavaFile();
	}

	/**
	 * 用 BufferedWriter 和 BufferedReader 拷贝文件
	 * 
	 * @throws IOException
	 */
	private static void test_BufferedWriter_BufferedReader_copyJavaFile() throws IOException {
		File fromFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/Demo06_test.java");
		File toFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/Demo06_test_复制.java");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		}
		bufferedReader.close();
		bufferedWriter.close();
	}

	/**
	 * 用 FileReader 和 FileWriter 拷贝文件
	 * 
	 * @throws IOException
	 */
	private static void test_FileWriter_FileReader_copyJavaFile() throws IOException {
		File fromFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/Demo06_test.java");
		File toFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/Demo06_test_复制.java");

		FileReader fileReader = new FileReader(fromFile);
		FileWriter fileWriter = new FileWriter(toFile);
		char[] buf = new char[1024];
		int len = 0;
		while ((len = fileReader.read(buf)) != -1) {
			fileWriter.write(buf, 0, len);
		}
		fileReader.close();
		fileWriter.close();
	}
	
}
