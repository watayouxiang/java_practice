package com.watayouxiang.myjava.thread.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：一个属性是对象，但是整体不可变，其他类无法修改set里面的数据
 */
public class ImmutableDemo {

    private final Set<String> student = new HashSet<>();

    public ImmutableDemo() {
        student.add("xiaoWang");
        student.add("xiaoLi");
        student.add("xiaoPeng");
    }

    public boolean isStudent(String name) {
        return student.contains(name);
    }
}
