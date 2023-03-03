package com.watayouxiang.myjava.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/*
Set：元素是无序(存入和取出的顺序可能不一致)，元素不可以重复。

    1、Set如何保证元素的唯一性呢？
        是通过元素的两个方法，hashCode 和 equals 来完成。
        如果元素的 HashCode 值相同，才会判断 equals 是否为 true。
        如果元素的 hashCode 值不同，不会调用 equals。

    2、Set的子类：

	    HashSet: 哈希表数据结构，线程不同步，无序的。

	    TreeSet：二叉树数据结构，有序的。
	            可以给集合中的元素进行排序
				第一种排序方式：
					元素实现 java.lang.Comparable 接口的 compareTo 方法
					让元素自身具备比较性
				第二种排序方式:
					TreeMap构造时传入 java.util.Comparator比较器，通过compare方法排序
					让集合自身具备比较性。

 */
public class Demo_Set {

    public static void main(String[] args) {
        test_HashSet();
        treeSet_Comparable();
        treeSet_Comparator();
    }

    private static void treeSet_Comparator() {
        // 根据名字长度排序
        TreeSet<Person> set = new TreeSet<Person>(new MyComparator());
        set.add(new Person("wangwu", 11));
        set.add(new Person("lisi", 11));
        set.add(new Person("zhangsan", 12));
        set.add(new Person("zhangsan", 12));
        set.add(new Person("niubihonghong", 10));
        set.add(new Person("zhangsan", 16));

        Iterator<Person> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private static void treeSet_Comparable() {
        // 根据年龄大小排序
        TreeSet<Person> set = new TreeSet<Person>();
        set.add(new Person("zhangsan", 12));
        set.add(new Person("zhangsan", 12));
        set.add(new Person("wangwu", 11));
        set.add(new Person("lisi", 11));
        set.add(new Person("niubihonghong", 10));
        set.add(new Person("zhangsan", 16));

        Iterator<Person> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
    }

    private static void test_HashSet() {
        // hashCode 和 equals 来保证唯一性
        HashSet<Person> set = new HashSet<Person>();
        set.add(new Person("wangwu", 11));
        set.add(new Person("wangwu", 11));
        set.add(new Person("zhangsan", 12));
        set.add(new Person("zhangsan", 12));
        set.add(new Person("lisi", 11));
        set.add(new Person("lisi", 11));
        set.add(new Person("lisi", 11));

        Iterator<Person> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
    }

    //==============================================================================================

    static class MyComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            // 根据名字长度排序
            return o1.name.length() - o2.name.length();
        }
    }

    static class Person implements Comparable<Person> {
        public String name;
        public int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age * 37;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Person)) return false;
            Person p = (Person) obj;
            return this.name.equals(p.name) && this.age == p.age;
        }

        @Override
        public int compareTo(Person o) {
            // 根据年龄大小排序
            return this.age - o.age;
        }
    }

}