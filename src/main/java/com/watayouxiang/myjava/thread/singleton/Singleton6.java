package com.watayouxiang.myjava.thread.singleton;

/**
 * 描述：     双重检查（线程安全）（推荐用）
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            // synchronized 只能保证 "原子性" 和 "可见性"
            // 但想要线程安全的话，还需要保证 "有序性"
            //
            // 因为新建对象 instance = new Singleton6(); 实际上有3个步骤
            // 所以 instance 必须要用 volatile 修饰来保证其 "有序性"
            // 如果不用 volatile 修饰，那么假如把 "新建对象的3个步骤" 进行指令重排序的话，就会出现安全问题
            synchronized (Singleton6.class) {
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
