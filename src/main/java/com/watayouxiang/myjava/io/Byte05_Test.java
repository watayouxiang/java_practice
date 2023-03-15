package com.watayouxiang.myjava.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Byte05_Test {

	public static void main(String[] args) throws IOException {
//		testCopy_FileOutStream_FileInputStream();
		testCopy_BufferedOutputStream_BufferedInputStream();

	}

	/**
	 * 用 BufferedOutputStream 和 BufferedInputStream 拷贝文件
	 * 
	 * @throws IOException
	 */
	private static void testCopy_BufferedOutputStream_BufferedInputStream() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("README.md")));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("README_copy.md")));
		int by = 0;
		while ((by = bis.read()) != -1) {
			bos.write(by);
		}
		bos.close();
		bis.close();
	}

	/**
	 * 用 FileOutStream 和 FileInputStream 拷贝文件
	 * 
	 * @throws IOException
	 */
	private static void testCopy_FileOutStream_FileInputStream() throws IOException {
		FileInputStream bis = new FileInputStream(new File("README.md"));
		FileOutputStream bos = new FileOutputStream(new File("README_copy.md"));
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}
		bos.close();
		bis.close();
	}

}
