package com.watayouxiang.myjava.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/*

Writer
	--| OutputStreamWriter

 */
public class Char07_OutputStreamWriter {

	public static void main(String[] args) throws IOException {
		PrintStream printStream = System.out;
		// 将 OutputStream 转化成 Writer。并指定编码
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(printStream, "UTF-8");
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		// 会自动关闭 outputStreamWriter，printStream
		bufferedWriter.close();
	}

}
