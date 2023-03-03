package com.watayouxiang.myjava.clazz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/*

Properties类是属于java.util包中的类
Properties类是HashTable的子类，所以Properties类也就是Map键值对
Properties类：用于键值对形式的配置文件

Properties类的方法：
setProperty(String key, String value): 设置键值对
getProperty(String key): 获取键值对
Set<String> stringPropertyNames(): 获取键的集合
load(InputStream inStream): 將InputStream流转变成Properties键值对集合
list(PrintStream out): 將Properties键值对集合内容输出到指定的输出流中去
store(OutputStream out, String comments): 將Properties集合中内容存到指定的输出流中去

 */
public class Demo_Properties {

    public static void main(String[] args) throws IOException {
//        properties_set_get();
//        readFile2Properties();
        testPropertiesDemo();
    }

    /**
     * 功能同 readFile2Properties() 方法
     *
     * @throws IOException
     */
    private static void testPropertiesDemo() throws IOException {
        Properties prop = new Properties();
        File file = new File("properties.prop");
        File file_new = new File("properties_new.prop");

        // 将数据流加载进集合
        prop.load(new FileInputStream(file));

        // 往prop中添加键值对，但是没有把修改后的结果存到info.txt中
        prop.setProperty("mother", "yao");

        // 將prop键值对集合内容打印到指定的输出流中去
        prop.list(System.out);

        // 将prop集合中的内容存到指定的输出流中，comments是注释信息，可写可不写
        prop.store(new FileOutputStream(file_new), "comments");
        System.out.println("文件保存路径：" + file_new.getAbsoluteFile());
    }

    /**
     * 将 info.txt 的数据读到 Properties 中
     */
    private static void readFile2Properties() throws IOException {
        Properties pro = new Properties();
        BufferedReader br = new BufferedReader(new FileReader("info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("=");
            pro.setProperty(arr[0], arr[1]);
        }
        br.close();
        System.out.println(pro);
    }

    /**
     * getter setter 方法
     */
    private static void properties_set_get() {
        Properties prop = new Properties();//是map类型集合
        prop.setProperty("name", "t.t");
        prop.setProperty("age", "23");
        prop.setProperty("time", "2014.6.1");

        System.out.println("----- keySet -----");
        Set<String> keys = prop.stringPropertyNames();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = prop.getProperty(key);
            System.out.println("key = " + key + ", value = " + value);
        }

        System.out.println("----- entrySet -----");
        Set<Entry<Object, Object>> entrySet = prop.entrySet();
        Iterator<Entry<Object, Object>> it2 = entrySet.iterator();
        while (it2.hasNext()) {
            Entry<Object, Object> entry = it2.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }
    }

}
