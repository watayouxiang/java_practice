package com.watayouxiang.myjava.jdk.jdk16;

public class App {
    public static void main(String[] args) {
        User user = new User("zhangsan","123456",18,null);
        System.out.println(user.username());
        System.out.println(user.password());
        System.out.println(user.toString());
    }
}
