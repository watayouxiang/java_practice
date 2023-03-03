package com.watayouxiang.myjava.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*

Writer
--| BufferedWriter 带缓冲区的写字符流（只要用到缓冲区，就要记得刷新）

 */
public class Char03_BufferedWriter {

	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());

		// 如果文件不存在会自动创建，存在则会覆盖
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("hello java");
		bufferedWriter.newLine();
		bufferedWriter.write("hello BufferedWriter");
		bufferedWriter.flush();
		// fileWriter 也会一同关闭
		bufferedWriter.close();
	}

}
