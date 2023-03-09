package com.watayouxiang.myjava.thread.singleton;

/**
 * 描述：     懒汉式（线程安全）
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
