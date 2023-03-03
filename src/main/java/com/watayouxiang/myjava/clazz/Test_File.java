package com.watayouxiang.myjava.clazz;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Test_File {

    public static void main(String[] args) {
        File inFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io");
        File outFile = new File(System.getProperty("user.dir") + "/src/com/wata/javademo/io/outFile.txt");

//		LinkedList<String> list = new LinkedList<String>();
//		getFileStructure(inFile, 0, list);
//		System.out.println(list);

//		outputFileStructure(inFile, System.out);

        saveFileStructure(inFile, outFile);
    }

    /**
     * 将inFile的目录结构保存至outFile中
     *
     * @param inFile  源File
     * @param outFile 目标File
     */
    private static void saveFileStructure(File inFile, File outFile) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            outputFileStructure(inFile, bufferedOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将file的目录结构信息，输出到outputStream
     *
     * @param file         文件或者目录
     * @param outputStream 字节输出流
     */
    private static void outputFileStructure(File file, OutputStream outputStream) {
        // 获取inFile的目录结构，保存到linkedList
        LinkedList<String> linkedList = new LinkedList<String>();
        getFileStructure(file, 0, linkedList);
        // 将目录结构输出到
        PrintStream printStream = new PrintStream(outputStream);
        for (String string : linkedList) {
            printStream.println(string);
        }
        printStream.close();
    }

    /**
     * 获取file的目录结构，保存到list中
     *
     * @param file  文件或者目录
     * @param level 初始层级
     * @param list  用于保存目录结构的集合
     */
    private static void getFileStructure(File file, int level, List<String> list) {
        list.add(getTag(level) + file.getName());
        level++;
        // 当file不为目录 或者 报SecurityException错时，返回为null
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 如果目录下还有目录，那么递归遍历
                getFileStructure(files[i], level, list);
            }
        }
    }

    /**
     * 获取层级标识
     *
     * @param level 层级
     * @return
     */
    private static String getTag(int level) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < level; x++) {
            sb.append("|--");
        }
        return sb.toString();
    }

}
