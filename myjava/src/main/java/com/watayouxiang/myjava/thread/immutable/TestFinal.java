package com.watayouxiang.myjava.thread.immutable;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：测试final能否被修改
 */
public class TestFinal {
    public static void main(String[] args) {
        // 具有不可变性的对象一定是线程安全的，我们不需要采取任何额外的安全措施，也能保证线程安全
        Person person = new Person();
        int age = person.age;
    }
}
