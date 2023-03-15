package com.watayouxiang.myjava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*

Reader
	--| InputStreamReader

 */
public class Char06_InputStreamReader {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		// 将 InputStream 转化成 Reader。并指定编码
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		// 会自动关闭 inputStreamReader，inputStream
		bufferedReader.close();
	}

}
