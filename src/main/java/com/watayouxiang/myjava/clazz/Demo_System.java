package com.watayouxiang.myjava.clazz;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class Demo_System {

    public static void main(String[] args) throws FileNotFoundException {
        printProperties();
    }

    private static void printProperties() throws FileNotFoundException {
        // 获取 描述系统属性信息
        Properties prop = System.getProperties();

        // 将Properties信息保存到文件中
		prop.list(new PrintStream("properties.prop"));

        // 在系统中自定义一些特有信息。
        System.setProperty("wata", "watayouxiang");

        System.out.println("------- 打印 Properties 信息 -------");
        Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, Object> entry = iterator.next();
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }

        System.out.println("------- 获取指定属性信息 -------");
        String user_dir = System.getProperty("user.dir");
        System.out.println("user_dir = " + user_dir);
    }

}
