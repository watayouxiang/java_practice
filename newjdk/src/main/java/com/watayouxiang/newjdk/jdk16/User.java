package com.watayouxiang.newjdk.jdk16;

public  record User(String username , String password , Integer age , User parent){

     /*
        1.record类不允许使用abstract关键字定义为抽象
        2.所有成员变量均为final修饰，不允许再次赋值
        3.不允许实现成员变量
        private Float height;
        4.允许出现静态变量/实例方法/静态方法
        5.允许出现其他构造方法，但必须调用record构造方法
        6.record不允许extends继承其他类
    */
    public User(String username , String password , Integer age){
        this(username, password, age, null);
    }
    private static String desc;
    public void showInfo(){

    }
}
