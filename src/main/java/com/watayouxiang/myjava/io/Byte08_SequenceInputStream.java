package com.watayouxiang.myjava.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/*
SequenceInputStream类：用于將多个InputStream(源)合并起来

构造函数：
SequenceInputStream(InputStream s1, InputStream s2)
將两个InputStream(源)合并起来。

SequenceInputStream(Enumeration<? extends InputStream> e)
將多个InputStream(源)合并起来。

 */
public class Byte08_SequenceInputStream {

    public static void main(String[] args) throws IOException {
		combineFiles();
		splitFile();
        mergeFile();
    }

    /**
     * 合并文件
     */
    private static void mergeFile() throws IOException {
        ArrayList<BufferedInputStream> list = new ArrayList<>();
        // 将.part文件加载进集合
        for (int i = 1; i <= 5; i++) {
            list.add(new BufferedInputStream(new FileInputStream(new File("tao-" + i + ".part"))));
        }
        // 转存到 Enumeration
        final Iterator<BufferedInputStream> it = list.iterator();
        Enumeration<BufferedInputStream> en = new Enumeration<BufferedInputStream>() {
            @Override
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override
            public BufferedInputStream nextElement() {
                return it.next();
            }
        };

        // 合并.part文件
        SequenceInputStream sis = new SequenceInputStream(en);
        File outFile = new File("tao-merge.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        byte[] buf = new byte[1024];
        int len;
        while ((len = sis.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }

        bos.close();
        sis.close();

        System.out.println("文件合并完成 " + outFile.getAbsolutePath());
    }

    /**
     * 切割文件
     */
    private static void splitFile() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("tao.jpg"));

        BufferedOutputStream bos;
        byte[] buf = new byte[1024 * 10];// 按照每块10K的大小切割文件
        int len;
        int count = 0;
        while ((len = bis.read(buf)) != -1) {
            count++;
            bos = new BufferedOutputStream(new FileOutputStream("tao-" + count + ".part"));
            bos.write(buf, 0, len);
            bos.close();
        }

        bis.close();
        System.out.println("切割文件成功");
    }

    /**
     * 將多文件数据存到一个文件
     */
    private static void combineFiles() throws IOException {
        // 输入流集合
        Vector<FileInputStream> vector = new Vector<>();
        vector.add(new FileInputStream("1.txt"));
        vector.add(new FileInputStream("2.txt"));
        vector.add(new FileInputStream("3.txt"));
        Enumeration<FileInputStream> en = vector.elements();

        // 用于將多个InputStream(源)合并起来
        SequenceInputStream sis = new SequenceInputStream(en);

        // 输出流
        File outFile = new File("4.txt");
        FileOutputStream fos = new FileOutputStream(outFile);

        int len;
        byte[] buf = new byte[1024];
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }

        // 关闭流
        fos.close();
        sis.close();
        System.out.println("合并文件成功，查看 " + outFile.getAbsolutePath());
    }

}
