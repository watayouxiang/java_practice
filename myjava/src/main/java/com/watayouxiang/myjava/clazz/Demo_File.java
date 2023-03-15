package com.watayouxiang.myjava.clazz;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class Demo_File {

	public static void main(String[] args) {
		demo_newFile();
		demo_createFile();
		demo_rename();
		demo_delete();
		demo_getStatus();
		demo_listRoots();
		demo_listDir();
		demo_FilenameFilter();
	}

	private static void demo_FilenameFilter() {
		File dir = new File("/Applications");
		// 列出满足指定过滤器要求的所有文件和目录
		String[] arr = dir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains("Music");
			}
		});
		System.out.println(Arrays.toString(arr));
	}

	private static void demo_listDir() {
		// 列出指定路径的所有文件和目录
		File f = new File("/Applications");
		String[] arr = f.list();
		System.out.println(Arrays.toString(arr));
	}

	private static void demo_listRoots() {
		// 列出可用的文件系统根目录
		File[] files = File.listRoots();
		System.out.println(Arrays.toString(files));
	}

	private static void demo_getStatus() {
		File file = new File("/Users/TaoWang/Downloads/test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 判断是否存在
		boolean exists = file.exists();
		System.out.println("exists = " + exists);

		// 判断是否可执行
		boolean canExecute = file.canExecute();
		System.out.println("canExecute = " + canExecute);

		// 判断是否是一个文件
		boolean isFile = file.isFile();
		System.out.println("isFile = " + isFile);

		// 判断是否是一个目录
		boolean isDirectory = file.isDirectory();
		System.out.println("isDirectory = " + isDirectory);

		// 判断是否是一个绝对路径
		boolean isAbsolute = file.isAbsolute();
		System.out.println("isAbsolute = " + isAbsolute);

		// 获取相对路径
		String path = file.getPath();
		System.out.println("path = " + path);

		// 获取绝对路径
		String absolutePath = file.getAbsolutePath();
		System.out.println("absolutePath = " + absolutePath);
	}

	private static void demo_delete() {
		File file = new File("test.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 删除失败返回false（文件不存在/文件正在使用中）
		boolean delete = file.delete();
		System.out.println("delete = " + delete);

		// 在虚拟机终止时，删除目录
		file.deleteOnExit();
	}

	private static void demo_rename() {
		File file = new File("test.txt");
		File file2 = new File("test_rename.txt");

		// 重命名 file
		boolean renameTo = file.renameTo(file2);
		System.out.println("renameTo = " + renameTo);
	}

	private static void demo_createFile() {
		File file = new File("test.txt");
		File dir = new File("test");
		File dir_more = new File("test/aaa/bbb");

		try {
			// 创建文件，如果该文件已经存在，则不创建并返回false
			boolean createNewFile = file.createNewFile();
			System.out.println("createNewFile = " + createNewFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 创建文件夹，且只能创建一级目录
		boolean mkdir = dir.mkdir();
		System.out.println("mkdir = " + mkdir);

		// 创建多级文件夹，可以创建多级目录
		boolean mkdirs = dir_more.mkdirs();
		System.out.println("mkdirs = " + mkdirs);
	}

	private static void demo_newFile() {
		File file = new File("a.txt");
		System.out.println(file.getAbsolutePath());

		// separator 字段：与系统有关的路径分隔符
		File file2 = new File(System.getProperty("user.dir") + File.separator + "test.txt");
		System.out.println(file2.getAbsolutePath());

		// String, String
		File file3 = new File("bbb", "test.txt");
		System.out.println(file3.getAbsolutePath());

		// File, String
		File file4 = new File(new File("ccc"), "test.txt");
		System.out.println(file4.getAbsolutePath());
	}

}
