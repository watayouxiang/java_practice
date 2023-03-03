package com.watayouxiang.myjava.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
方法引用通过方法的名字来指向一个方法。

方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

方法引用使用一对冒号 :: 。

 */
public class Java8_02方法引用 {

    public static void main(String[] args) {
//        test01();
        test02();
    }

    private static void test02() {
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

//        names.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

//        names.forEach((s -> System.out.println(s)));

        //实例中我们将 System.out::println 方法作为静态方法来引用。
        names.forEach(System.out::println);
    }

    private static void test01() {
        final List<Car> cars = Arrays.asList(new Car(), new Car(), new Car());

        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Car car = Car.create(Car::new);

        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);

        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);

        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    static class Car {
        //Supplier是jdk1.8的接口，这里和lamda一起使用了
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    //该注解表示该接口为"函数接口"
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

}
