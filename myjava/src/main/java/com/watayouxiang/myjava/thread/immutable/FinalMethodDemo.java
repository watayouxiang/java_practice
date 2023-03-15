package com.watayouxiang.myjava.thread.immutable;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：final方法
 */
public class FinalMethodDemo {

    public void drink() {
    }

    public final void eat() {
    }

    public static void sleep() {
    }
}

class SubClass extends FinalMethodDemo {
    @Override
    public void drink() {
        super.drink();
    }


}
