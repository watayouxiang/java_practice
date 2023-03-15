package com.watayouxiang.myjava.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*

DataInputStream 与 DataOutputStream: 可以用于操作基本数据类型的数据的流对象。

方法:

writeInt(int v)：            将一个 int 值以 4-byte 值形式写入
writeBoolean(boolean v)：    将一个 boolean 值以 1-byte 值形式写入
writeDouble(double v)：      将一个 double 值以 8-byte 值形式写入
writeUTF()：                 使用 UTF-8 编码写入数据

readInt()
readBoolean()
readDouble()
readUTF()：                  用 UTF-8 编码读取数据

 */
public class Byte10_DataStream {

    public static void main(String[] args) throws IOException {
        writeData();
        readData();
        writeUTFDemo();
        readUTFDemo();
    }

    /**
     * 用 DataInputStream 中的 readUTF 方法从 data.txt 文件中取数据
     *
     * @throws IOException
     */
    private static void readUTFDemo() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("utf_date.txt"));
        String s = dis.readUTF();
        System.out.println(s);
        dis.close();
    }

    /**
     * 用 DataOutputStream 中的 writeUTF 方法写数据到 data.txt 文件中去
     *
     * @throws IOException
     */
    private static void writeUTFDemo() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("utf_date.txt"));
        dos.writeUTF("你好");
        dos.writeUTF("你好");
        dos.writeUTF("你好");
        dos.writeUTF("你好");
        dos.close();
    }

    /**
     * 用 DataInputStream 从 data.txt 文件中取数据
     *
     * @throws IOException
     */
    private static void readData() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));

        int _int = dis.readInt();
        boolean _boolean = dis.readBoolean();
        double _double = dis.readDouble();

        System.out.println("_int = " + _int);
        System.out.println("_boolean = " + _boolean);
        System.out.println("_double = " + _double);

        dis.close();
    }

    /**
     * 用 DataOutputStream 写数据到 data.txt 文件中去
     *
     * @throws IOException
     */
    private static void writeData() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));

        dos.writeInt(234);
        dos.writeBoolean(true);
        dos.writeDouble(9887.543);

        dos.close();
    }

}
