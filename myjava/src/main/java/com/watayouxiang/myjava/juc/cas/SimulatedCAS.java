package com.watayouxiang.myjava.juc.cas;

/**
 * author：wangtao
 * email：watayouixang@qq.com
 * time：2023/3/13
 * description：模拟CAS操作，等价代码
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if(oldValue == expectedValue){
            value = newValue;
        }
        return oldValue;
    }
}
