package com.watayouxiang.myjava.newjdk.jdk15;

public class HiddenClassesSample1 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };
    }
}
