package com.watayouxiang.myjava.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*

Reader
--| BufferedReader 带缓冲区的读字符流（只要用到缓冲区，就要记得刷新）

 */
public class Char04_BufferedReader {

	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		if (!file.exists()) {
			System.out.println("文件不存在");
			return;
		}
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line = null;
		// 读取一行，如果已到达流末尾，则返回null
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		// fileReader 也会一同关闭
		bufferedReader.close();
	}

}
