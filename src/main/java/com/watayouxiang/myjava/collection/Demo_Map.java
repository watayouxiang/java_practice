package com.watayouxiang.myjava.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/*
Map：该集合存储键值对，要保证键的唯一性。

	1、Map如何保证key的唯一性呢？
		是通过元素的两个方法，hashCode和equals来完成。
		如果HashCode值相同，才会判断equals是否为true。
		如果hashCode值不同，不会调用equals。

	2、Map的两种取出方式：
		1，第一种取出方式 keySet
		2，第二种取出方式 entrySet

    3、Map的子类

	    HashTable：1) 哈希表数据结构，无序。
	            2) 不可以存入null键null值。
				3) 该集合是线程同步的，效率低，jdk1.0

	    HashMap：1) 哈希表数据结构，无序。
	            2) 允许使用null键null值。
				3) 该集合是不同步的，效率高，将HashTable替代，jdk1.2
                4) LinkedHashMap: 链表结构，有序。

	    TreeMap：1) 底层是二叉树数据结构，有序
	            2) 该集合是不同步的
				3) 可以给集合中的Key进行排序
				第一种排序方式：
					元素实现 java.lang.Comparable 接口的 compareTo 方法
					让元素自身具备比较性
				第二种排序方式:
					TreeMap构造时传入 java.util.Comparator比较器，通过compare方法排序
					让集合自身具备比较性。

 */
public class Demo_Map {

    public static void main(String[] args) {
        baseMethod();
        iterator_keySet();
        iterator_entrySet();
        treeMap_Comparable();
        treeMap_Comparator();
    }

    private static void treeMap_Comparator() {
        System.out.println("--- treeMap Comparator ---");

        // 排序规则，name优先age其次
        TreeMap<Student, String> tm = new TreeMap<Student, String>(new StuComparator());
        tm.put(new Student("zhangsan", 23), "北京");
        tm.put(new Student("lisi", 21), "天津");
        tm.put(new Student("wangwu", 26), "上海");
        tm.put(new Student("xiaoming", 23), "南京");
        tm.put(new Student("laoli", 24), "武汉");

        Set<Entry<Student, String>> entrySet = tm.entrySet();
        Iterator<Entry<Student, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Entry<Student, String> me = it.next();
            Student stu = me.getKey();
            String addr = me.getValue();
            System.out.println(stu + " : " + addr);
        }

        System.out.println();
    }

    private static void treeMap_Comparable() {
        System.out.println("--- treeMap Comparable ---");

        // 排序规则，age优先name其次
        TreeMap<Student, String> tm = new TreeMap<Student, String>();
        tm.put(new Student("zhangsan", 23), "北京");
        tm.put(new Student("lisi", 21), "天津");
        tm.put(new Student("wangwu", 26), "上海");
        tm.put(new Student("xiaoming", 23), "南京");
        tm.put(new Student("laoli", 24), "武汉");

        Set<Entry<Student, String>> entrySet = tm.entrySet();
        Iterator<Entry<Student, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Entry<Student, String> me = it.next();
            Student stu = me.getKey();
            String addr = me.getValue();
            System.out.println(stu + " : " + addr);
        }

        System.out.println();
    }

    private static void iterator_entrySet() {
        System.out.println("--- entrySet ---");

        Map<Student, String> hm = new HashMap<Student, String>();
        hm.put(new Student("zhangsan", 21), "北京");
        hm.put(new Student("lisi", 21), "天津");
        hm.put(new Student("wangwu", 22), "上海");
        hm.put(new Student("xiaoming", 23), "南京");
        hm.put(new Student("laoli", 24), "武汉");

        Set<Entry<Student, String>> entrySet = hm.entrySet();
        Iterator<Entry<Student, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<Student, String> entry = iterator.next();
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }

        System.out.println();
    }

    private static void iterator_keySet() {
        System.out.println("--- keySet ---");

        Map<Student, String> hm = new HashMap<Student, String>();
        hm.put(new Student("zhangsan", 21), "北京");
        hm.put(new Student("lisi", 21), "天津");
        hm.put(new Student("wangwu", 22), "上海");
        hm.put(new Student("xiaoming", 23), "南京");
        hm.put(new Student("laoli", 24), "武汉");

        Set<Student> keySet = hm.keySet();
        Iterator<Student> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Student key = iterator.next();
            String value = hm.get(key);
            System.out.println(key + " : " + value);
        }

        System.out.println();
    }

    private static void baseMethod() {
        System.out.println("--- baseMethod ---");
        Map<String, String> map = new HashMap<String, String>();

        System.out.println("// 如果添加相同的键，那么后添加的值会覆盖原有的值，并返回被覆盖的值。");
        System.out.println("put:" + map.put("01", "zhangsan"));
        System.out.println("put:" + map.put("02", "lisi"));
        System.out.println("put:" + map.put("02", "wangwu"));

        System.out.println("// 如果移除成功，会返回键对应的值");
        System.out.println("containsKey:" + map.containsKey("022"));
        System.out.println("remove:" + map.remove("02"));
        System.out.println("get:" + map.get("01"));

        System.out.println("// HashMap 允许 null 值和 null 键");
        System.out.println("put:" + map.put("03", null));
        System.out.println("put:" + map.put(null, "xiaoming"));

        System.out.println("// 打印Map");
        System.out.println(map);

        System.out.println("// 获取map集合中所有的键");
        Set<String> keySet = map.keySet();
        System.out.println(keySet);

        System.out.println("// 获取map集合中所有的值");
        Collection<String> values = map.values();
        System.out.println(values);

        System.out.println();
    }

    // ==========================================================================================

    static class StuComparator implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
            int num = s1.age - s2.age;
            if (num != 0) return num;
            return s1.name.compareTo(s2.name);
        }
    }

    static class Student implements Comparable<Student> {
        public String name;
        public int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Student s) {
            int num = this.age - s.age;
            if (num != 0) return num;
            return this.name.compareTo(s.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age * 34;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Student)) return false;
            Student s = (Student) obj;
            return this.name.equals(s.name) && this.age == s.age;
        }
    }
}