package com.watayouxiang.myjava.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * RandomAccessFile类：
 * 具备读和写功能，内部封装了一个数组，而且通过指针对数组的元素进行操作
 *
 * 方法：
 * getFilePointer()：获取指针的位置
 * seek(long pos)：改变指针的位置
 * readInt()：从此文件读取一个有符号的 32 位整数（4字节）
 * skipBytes(int n)： 尝试跳过 n 个字节
 *
 * 构造函数：
 * Char10_RandomAccessFile(File file, String mode)
 * 通过构造方法可以看出，该类只能操作文件。而且操作文件还有模式
 *
 * --------------------------------------------
 *
 * raf.write(97);//注意：write方法只写出int类型的最低8位
 */
public class Char10_RandomAccessFile {

    public static void main(String[] args) throws IOException {
        writeFile();
        writeFile2();
        ReadFile();
    }

    private static void ReadFile() throws IOException {
        //源是ran.txt，模式是只读
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "r");

        //windows默认编码是GBK，macOS默认编码是UTF-8
        //所以windows中一个中文字符2个字节，macOS中一个中文3个字节
        //因为“李四”是6个字节，而“97”也是4字节
        //所以调整指针到10，取出的就是“王五”，“99”
        raf.seek(10);//改变指针的位置

        //指针位置在10的地方，然后再跳过10个字节，所以取到的就是“小圆”，“95”
        raf.skipBytes(10);//指针跳过10个字节

        byte[] buf = new byte[6];//缓冲区数组buf
        raf.read(buf);//將数据读到缓冲区数组buf中
        System.out.println("name = " + new String(buf));
        System.out.println("age = " + raf.readInt());

        raf.close();
    }

    private static void writeFile2() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw");

        //指针指向30位置
        raf.seek(30);

        raf.write("周七".getBytes());
        raf.writeInt(103);

        raf.close();
    }

    private static void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("ran.txt", "rw");

        //windows默认编码是GBK，macOS默认编码是UTF-8
        //所以windows中一个中文字符2个字节，macOS中一个中文3个字节
        raf.write("李四".getBytes());
        //用四个字节存储int类型数据
        raf.writeInt(97);

        raf.write("王五".getBytes());
        raf.writeInt(99);
        raf.write("小圆".getBytes());
        raf.writeInt(95);

        raf.close();
    }

}
