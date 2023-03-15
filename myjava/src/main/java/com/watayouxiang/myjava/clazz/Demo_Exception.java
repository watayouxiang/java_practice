package com.watayouxiang.myjava.clazz;

/*
## Exception简介

1、异常体系

	Throwable
	|--Error
	|--Exception
	|--|--RuntimeException

2、异常的处理

	try {
		需要被检测的代码；
	} catch (异常类 变量) {
		处理异常的代码；
	} finally {
		一定会执行的语句；
	}

3、异常对象常见方法操作

	String getMessage()：获取异常信息。
	toString()：返回此 throwable 的简短描述。
	printStackTrace()：将此 throwable 及其追踪输出至标准错误流。

4、对多异常的处理。

	1）声明异常时，建议声明更为具体的异常。这样处理的可以更具体。
	2）对方声明几个异常，就对应有几个catch块。不要定义多余的catch块。
		如果多个catch块中的异常出现继承关系，父类异常catch块放在最下面。
	3）建议在进行catch处理时，catch中一定要定义具体处理方式。
		不要简单定义一句 e.printStackTrace(),也不要简单的就书写一条输出语句。

5、RuntimeException：

	对于异常分两种：
		1）编译时被检测的异常。
		2）编译时不被检测的异常(运行时异常。RuntimeException以及其子类)
	Exception中有一个特殊的子类异常RuntimeException 运行时异常。
		如果在函数内容抛出该异常，函数上可以不用声明，编译一样通过。
		如果在函数上声明了该异常。调用者可以不用进行处理。编译一样通过；
		之所以不用在函数声明，是因为不需要让调用者处理。当该异常发生，希望程序停止，并对代码进行修正。

## 自定义异常

1、如何定义异常信息呢？

	发现打印的结果中只有异常的名称，却没有异常的信息。
	因为自定义的异常并未定义信息。

	因为父类中已经把异常信息的操作都完成了。
	所以子类只要在构造时，将异常信息传递给父类通过super语句。
	那么就可以直接通过getMessage方法获取自定义的异常信息。

 2、自定义异常继承Exception：

	必须是自定义类继承Exception。

	继承Exception原因：
	异常体系有一个特点：因为异常类和异常对象都被抛出。
	他们都具备可抛性。这个可抛性是Throwable这个体系中独有特点。

3、throws和throw的区别：

	只有这个体系中的类和对象才可以被throws和throw操作。

	throws使用在函数上。
	throw使用在函数内。

	throws后面跟的异常类。可以跟多个。用逗号隔开。
	throw后跟的是异常对象。
 */
public class Demo_Exception {

    public static void main(String[] args) {
        testException();
        testRuntimeException();
    }

    private static void testRuntimeException() {
        // 由于是运行时异常，所以可以不处理
        // 因为运行时异常，本来就是要求程序停止，对代码进行修改
        System.out.println(div2(10, -1));
        System.out.println("-- test02 END --");
    }

    private static void testException() {
        try {
            System.out.println(div(10, -1));// 必须要处理的异常
        } catch (MyException e) {
            System.out.println("错误的负数是：" + e.getValue());
            System.out.println(e.toString());// 返回此 throwable 的简短描述
            System.out.println(e.getMessage());// 获取异常信息
            e.printStackTrace();// 将此 throwable 及其追踪输出至标准错误流
        } finally {
            //finally代码块定义了一定会执行的代码，通常用于关闭资源。
            //系统退出，JVM结束
            //System.exit(0);
        }

        System.out.println("-- test01 END --");
    }

    private static int div(int a, int b) throws MyException {// throws使用在函数上
        if (b < 0)
            throw new MyException("除数是负数异常", b);// throw使用在函数内
        return a / b;
    }

    private static int div2(int a, int b) throws MyRuntimeException {
        if (b < 0)
            throw new MyRuntimeException("除数是负数异常");
        return a / b;
    }

    //==================================================
    // 内部类
    //==================================================

    /**
     * 自定义 RuntimeException（编译时不被检测的异常）
     *
     * @author TaoWang
     */
    static class MyRuntimeException extends RuntimeException {
        static final long serialVersionUID = -3387516993124229948L;

        MyRuntimeException(String msg) {
            super(msg);
        }
    }

    /**
     * 自定义Exception（编译时被检测的异常）
     *
     * @author TaoWang
     */
    static class MyException extends Exception {
        static final long serialVersionUID = -3387516993124229948L;
        private int value;

        MyException(String msg, int value) {
            // 通过 super 语句，将异常信息传递给父类
            // 那么就可以直接通过 getMessage() 方法获取自定义的异常信息
            super(msg);
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }

}