package com.watayouxiang.myjava.io;

import java.io.IOException;
import java.io.PrintStream;

/*

OutputStream
	--| PrintStream 打印输出流


log4j工具包专门用于生成日志的

 */
public class Byte06_PrintStream {

	public static void main(String[] args) throws IOException {
		try {
			int[] arr = new int[2];
			System.out.println(arr[3]);
		} catch (Exception e) {
			e.printStackTrace();

			// 创建 PrintStream
			PrintStream printStream = new PrintStream("error.log");
			// 将异常信息打印到指定 PrintStream
			e.printStackTrace(printStream);
		}
	}
}
