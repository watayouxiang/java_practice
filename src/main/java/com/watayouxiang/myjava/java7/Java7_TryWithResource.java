package com.watayouxiang.myjava.java7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Java7_TryWithResource {

    public static void main(String[] args) throws IOException {
        function();
        function7();
    }

    private static void function7() throws IOException {
        /*
         * try(必须是java.lang.AutoCloseable的子类对象){}//资源的自动释放。不要close。只要将需要释放资源的定义在try()中
         * 好处：省去了调用close，省去了finally。流技术的对象都是这个接口的子类。
         */
        try (FileReader fr = new FileReader("temp.txt");
             FileWriter fw = new FileWriter("temp2.txt")) {
            int read = fr.read();
            fw.write("abc");
        }
    }

    private static void function() throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("temp.txt");
            fw = new FileWriter("temp2.txt");
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }
    }
}
