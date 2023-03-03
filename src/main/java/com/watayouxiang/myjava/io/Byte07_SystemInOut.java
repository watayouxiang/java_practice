package com.watayouxiang.myjava.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/*

System.out	标准输出设备，控制台
System.in	标准输入设备，键盘

 */
public class Byte07_SystemInOut {

	public static void main(String[] args) throws IOException {
		InputStream in = System.in;
		PrintStream out = System.out;
		StringBuilder sb = new StringBuilder();

		while (true) {
			int ch = in.read();
			if (ch == '\n') {
				String s = sb.toString();
				if ("over".equals(s)) {
					out.println("--- 程序已退出 ---");
					break;
				}
				out.println("大写: " + s.toUpperCase());
				sb.delete(0, sb.length());
			} else {
				sb.append((char) ch);
			}
		}
	}

}
