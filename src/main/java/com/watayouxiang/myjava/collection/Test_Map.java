package com.watayouxiang.myjava.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test_Map {

    public static void main(String[] args) {
        // 1
        String str = "sdfg+zxcva/sdfx,cvdf,AHKKC";
        countCharNum("字符串中字母出现的次数：" + str);

        // 2
        HashMap<Integer, User> users = new HashMap<>();
        users.put(1, new User("张三", 25));
        users.put(3, new User("李四", 22));
        users.put(2, new User("王五", 28));
        System.out.println(users);

        HashMap<Integer, User> sortUsers = sortHashMap(users);
        System.out.println("HashMap排序：" + sortUsers);

        // 3
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三"));
        students.add(new Student(1, "张三"));
        students.add(new Student(2, "李四"));
        students.add(new Student(3, "王五"));
        students.add(new Student(4, "孙八"));
        students.add(new Student(5, "小王"));
        System.out.println(students);

        List<Student> students2 = new ArrayList<>();
        students2.add(new Student(1, "张三"));
        students2.add(new Student(2, "李四"));
        students2.add(new Student(3, "王五"));
        System.out.println(students2);

        List<Student> common = getCommon(students2, students);
        System.out.println("相同元素：" + common);
    }

    // =====================================================================================

    /**
     * 取两个List中的相同元素集合，按照从小到大排序返回。
     * 考虑时间复杂度、空间复杂度，求最优解法？
     *
     * @param list1 列表1
     * @param list2 列表2
     * @return 相同的元素集
     */
    private static List<Student> getCommon(List<Student> list1, List<Student> list2) {
        // 去重复，value代表是否重复
        HashMap<Student, Boolean> map = new HashMap<>(list2.size());
        for (Student stu : list2) {
            map.put(stu, false);
        }

        for (Student stu : list1) {
            if (map.get(stu) != null) {
                // 存在
                map.put(stu, true);
            } else {
                // 不存在
                map.put(stu, false);
            }
        }

        List<Student> common = new ArrayList<>();
        for (Map.Entry<Student, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                common.add(entry.getKey());
            }
        }

        Collections.sort(common);

        return common;
    }

    public static class Student implements Comparable<Student> {
        public int id;
        public String name;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Student)) return false;
            Student st = (Student) obj;
            if (name == null) {
                return st.name == null;
            }
            return (name.equals(st.name));
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Student o) {
            return id - o.id;
        }
    }

    // =====================================================================================

    /**
     * Question:
     * 已知一个 HashMap<Integer，User>集合， User 有 name(String)和 age(int)属性。
     * 请写一个方法实现 对 HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，
     * 返回类型为 HashMap<Integer，User>， 要求对 HashMap 中的 User 的 age 倒序进行排序。
     * 排序时 key=value 键值对不得拆散。
     * <p>
     * Answer:
     * HashMap 本身就是不可排序的，但是该道题偏偏让给 HashMap 排序，
     * 那我们就得想在 API 中有没有这样的 Map 结构是有序的，LinkedHashMap，
     * 他是 Map 结构，也是链表结构，有序的，更可喜的是他是 HashMap 的子类，
     * 我们返回 LinkedHashMap<Integer,User> 即可。
     */
    private static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> map) {
        // Map 转成 Set
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        // Set是Collection子类，所以可以转成List
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entries);
        // Collections.sort(List, Comparator) 排序 List
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                // 从大到小
                return o2.getValue().age - o1.getValue().age;
            }
        });
        // 创建一个有序的 HashMap 子类集合 LinkedHashMap
        // 因为 Linked 链表数据结构是按顺序放置元素的
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> user : list) {
            linkedHashMap.put(user.getKey(), user.getValue());
        }
        return linkedHashMap;
    }


    public static class User {
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String name;
        public int age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    // =====================================================================================

    /**
     * 统计一个字符串中字母出现的次数，结果以 a(1)c(2)..... 形式打印。
     * 例如："sdfg+zxcva/sdfx,cvdf,AHKKC"
     *
     * @param str 字符串
     */
    private static void countCharNum(String str) {
        char[] chs = str.toCharArray();

        TreeMap<Character, Integer> tm = new TreeMap<Character, Integer>();
        for (int x = 0; x < chs.length; x++) {
            // 无序统计
            if (!(chs[x] >= 'a' && chs[x] <= 'z' || chs[x] >= 'A' && chs[x] <= 'Z')) continue;

            // 开始统计
            Integer value = tm.get(chs[x]);
            if (value == null) {
                tm.put(chs[x], 1);
            } else {
                tm.put(chs[x], ++value);
            }
        }

        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Character, Integer>> entrySet = tm.entrySet();
        Iterator<Map.Entry<Character, Integer>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Integer> next = it.next();
            builder.append(next.getKey())
                    .append("(")
                    .append(next.getValue())
                    .append(")");
        }

        System.out.println(builder);
    }
}
