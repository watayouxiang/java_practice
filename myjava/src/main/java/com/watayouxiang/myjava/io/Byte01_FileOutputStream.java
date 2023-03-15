package com.watayouxiang.myjava.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*

OutputStream
	--| FileOutputStream 文件输出字节流

 */
public class Byte01_FileOutputStream {

	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		System.out.println(file.getAbsolutePath());
		byte[] bytes = "hello FileOutputStream!".getBytes();
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	
}
