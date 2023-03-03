package com.watayouxiang.myjava.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*

Writer
--| FileWriter 写字符流

 */
public class Char01_FileWriter {

	public static void main(String[] args) {
		writeString2File();
	}

	private static void writeString2File() {
		FileWriter fileWriter = null;
		try {
			File file = new File("test.txt");
			System.out.println(file.getAbsolutePath());

			// 没有文件则创建，有该文件则覆盖已有文件
			// fileWriter = new FileWriter(file);

			// 传递一个true参数，代表不覆盖已有的文件，并在已有文件的末尾处进行数据续写。
			fileWriter = new FileWriter(file, true);
			// 将字符串写入到流中
			fileWriter.write("test FileWriter!");
			// 刷新该流的缓冲
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					// 必须关闭
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
