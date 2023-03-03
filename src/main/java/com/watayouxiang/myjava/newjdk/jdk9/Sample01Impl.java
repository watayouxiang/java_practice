package com.watayouxiang.myjava.newjdk.jdk9;

public class Sample01Impl implements Sample01{
    @Override
    public void showDetail() {
        System.out.println("详细信息");
        System.out.println("网址:www.imooc.com");
    }

    public static void main(String[] args) {
        Sample01 sample01 = new Sample01Impl();
        sample01.showInfo();
        sample01.showDetail();
    }
}
