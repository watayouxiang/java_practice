package com.watayouxiang.myjava.pattern;

/*
单例设计模式:

最常见的有两种：“饿汉式”单例设计模式，“懒汉式”单例设计模式
建议使用“饿汉式”
*/
public class Demo_单例 {

    public static void main(String[] args) {
        Single1 single1 = Single1.getInstance();
        Single2 single2 = Single2.getInstance();
    }

    /**
     * 饿汉式：
     * Single类一进内存，就已经创建好了对象。
     */
    public static class Single1 {
        //1，构造函数私有化
        private Single1() {
        }

        //2，在类中创建一个对象
        private static Single1 mInstance = new Single1();

        //3，提供一个方法可以获取到该对象
        public static Single1 getInstance() {
            return mInstance;
        }
    }

    /**
     * 懒汉式：
     * 对象被调用的时候才会初始化，也叫对象的延时加载。
     * Single类进内存，对象还没有存在，
     * 只有调用了getInstance方法时候，才建立对象。
     */
    public static class Single2 {
        private Single2() {
        }

        private static Single2 mInstance = null;

        public static Single2 getInstance() {
            if (mInstance == null) {//减少判断次数，提高代码的效率。
                synchronized (Single2.class) {//因为会出现同步问题，所以加synchronized。
                    if (mInstance == null) {
                        mInstance = new Single2();
                    }
                }
            }
            return mInstance;
        }
    }

}