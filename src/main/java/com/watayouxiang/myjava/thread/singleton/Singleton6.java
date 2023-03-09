package com.watayouxiang.myjava.thread.singleton;

/**
 * 描述：     双重检查（推荐面试使用）（线程安全）（推荐用）
 */
public class Singleton6 {

    // 这里必须要用 volatile 修饰
    // 因为新建对象 instance = new Singleton6(); 实际上有3个步骤
    // 如果不用 volatile 修饰，那么假如把 新建对象的3个步骤 进行指令重排序，就会出现安全问题
    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
