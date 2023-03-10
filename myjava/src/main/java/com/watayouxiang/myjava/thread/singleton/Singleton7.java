package com.watayouxiang.myjava.thread.singleton;

/**
 * 描述：     静态内部类方式（懒汉式）（线程安全）（推荐用）
 */
public class Singleton7 {

    private Singleton7() {
    }

    private static class SingletonInstance {
        // 因为是内部类，所以不会被立马创建，只有当被使用时，才会被创建
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
