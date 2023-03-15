package com.watayouxiang.myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 可以识别中文的编码表有两个：GBK 和 UTF-8
 * <p>
 * GBK 编码是两个字节的编码（即：两个字节一个汉字）
 * UTF-8 是三个字节的编码（即：三个字节一个汉字）
 */
public class Char11_EncodeDecode {

    public static void main(String[] args) throws Exception {
        encodeDemo();
        decodeDemo();
        dealErrorDecode();

        lianTong();

        writeText();
        readText();
    }

    /**
     * 用 "字符转换流" 指定 "编码类型" 解码
     *
     * @throws IOException 异常
     */
    private static void readText() throws IOException {
        //把utf-8.txt文件以utf-8解码
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("utf-8.txt"), "utf-8");
        char[] buf = new char[2];//因为只有两个文字，所以定义两个字符大小缓冲即可
        int len = isr.read(buf);
        System.out.println(new String(buf, 0, len));
        isr.close();

        //把gbk.txt文件以gbk解码
        InputStreamReader isr2 = new InputStreamReader(
                new FileInputStream("gbk.txt"), "gbk");
        char[] buf2 = new char[2];//因为只有两个文字，所以定义两个字符大小缓冲即可
        int len2 = isr2.read(buf2);
        System.out.println(new String(buf2, 0, len2));
        isr2.close();
    }

    /**
     * 用 "字符转换流" 指定 "编码类型" 编码
     *
     * @throws IOException 异常
     */
    private static void writeText() throws IOException {
        //將 "你好" 两个字以 UTF-8 编码写到 utf-8.txt 文件中
        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("utf-8.txt"), "UTF-8");
        osw.write("你好");
        osw.close();

        //將 "你好" 两个字以 GBK 编码写到 gbk.txt 文件中
        OutputStreamWriter osw2 = new OutputStreamWriter(
                new FileOutputStream("gbk.txt"), "gbk");
        osw2.write("你好");
        osw2.close();
    }

    /**
     * 非常特殊的 "联通" 问题
     *
     * <p>
     * UTF-8的编码机制在API文档的java.io包DataInput接口中有。
     * 1）如果用一个字节表示的话，头位是0
     * 2）如果用二个字节表示的话，第一个字节头位是110，第二个字节头位是10
     * 3）如果用三个字节表示的话，第一个字节头位是1110，第二个字节头位是10，第三个字节头位是10
     * <p>
     * 字符 “联通” 的字节二进制编码是：
     * 11000001
     * 10101010
     * 11001101
     * 10101000
     * 刚好符合UTF-8的编码机制，所以解码的时候用的是UTF-8解码，所以以出现乱码。
     * <p>
     *
     * @throws Exception 异常
     */
    private static void lianTong() throws Exception {
        String str = "联通";
        byte[] bytes = str.getBytes("GBK");

        for (byte b : bytes) {
            //by的二进制形式是32位
            System.out.println(Integer.toBinaryString(b));
        }

        for (byte b : bytes) {
            //而我们需要的只是后8位，所以用 b & 255 操作获取后8位
            System.out.println(Integer.toBinaryString(b & 255));
        }
    }

    /**
     * 对错误解码进行重新编码和解码
     *
     * @throws Exception 异常
     */
    private static void dealErrorDecode() throws Exception {
        String str = "你好";

        //对str进行GBK编码
        byte[] b = str.getBytes("GBK");
        System.out.println(Arrays.toString(b));

        //对b进行错误的ISO8859-1解码
        String str2 = new String(b, "ISO8859-1");
        System.out.println(str2);

        //对str2进行ISO8859-1编码
        byte[] b2 = str2.getBytes("ISO8859-1");
        System.out.println(Arrays.toString(b2));

        //对b2进行GBK解码
        String str3 = new String(b2, "GBK");
        System.out.println(str3);

        //[-60, -29, -70, -61]
        //ÄãºÃ
        //[-60, -29, -70, -61]
        //你好
    }

    /**
     * 解码
     *
     * @throws Exception 异常
     */
    private static void decodeDemo() throws Exception {
        String str = "你好";

        //编码
        byte[] b1 = str.getBytes("GBK");
        byte[] b2 = str.getBytes("UTF-8");

        //解码
        String s1 = new String(b1, "GBK");
        String s2 = new String(b2, "UTF-8");
        String s3 = new String(b2);//windows默认编码是GBK，macOS默认编码是UTF-8

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3);
    }

    /**
     * 编码
     *
     * @throws Exception 异常
     */
    private static void encodeDemo() throws Exception {
        String str = "你好";

        //编码
        byte[] b1 = str.getBytes();//windows默认编码是GBK，macOS默认编码是UTF-8
        byte[] b2 = str.getBytes("GBK");//指定GBK编码，GBK编码是两个字节代表一个字符
        byte[] b3 = str.getBytes("UTF-8");//指定UTF-8编码，UTF-8编码是三个字节代表一个字符

        //打印编码结果
        System.out.println(Arrays.toString(b1));//[-28, -67, -96, -27, -91, -67]
        System.out.println(Arrays.toString(b2));//[-60, -29, -70, -61]
        System.out.println(Arrays.toString(b3));//[-28, -67, -96, -27, -91, -67]
    }

}
