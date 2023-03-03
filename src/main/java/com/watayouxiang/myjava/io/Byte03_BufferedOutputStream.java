package com.watayouxiang.myjava.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*

OutputStream
	--| BufferedOutputStream 带缓冲区的输出字节流（只要用到缓冲区，就要记得刷新）

 */
public class Byte03_BufferedOutputStream {

	public static void main(String[] args) throws IOException {

		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(file);
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write('h');
		bos.write("ello world!".getBytes());
		bos.flush();
		bos.close();

	}

}
