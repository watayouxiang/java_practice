package com.watayouxiang.myjava.java8;

/*
简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。

我们只需在方法名前面加个 default 关键字即可实现默认方法。
 */
public class Java8_04默认方法 {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }

    public static class Car implements Vehicle, FourWheeler {
        public void print(){
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }

    public interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车!");
        }
    }

    public interface Vehicle {
        default void print() {
            System.out.println("我是一辆车!");
        }

        // 静态方法
        static void blowHorn() {
            System.out.println("按喇叭!!!");
        }
    }


}
