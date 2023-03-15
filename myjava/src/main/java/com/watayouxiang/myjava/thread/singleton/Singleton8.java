package com.watayouxiang.myjava.thread.singleton;

/**
 * 描述：     枚举单例（线程安全）（最推荐用）
 * <p>
 * 1、写法简单
 * 2、线程安全有保证、懒加载
 * 3、避免反序列化破坏单例（避免被反射暴露）
 */
public enum Singleton8 {
    INSTANCE;

    public void whatever() {

    }
}
