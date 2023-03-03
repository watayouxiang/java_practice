package com.watayouxiang.myjava.clazz;

/*
Object:

是所有对象的直接后者间接父类，传说中的上帝。
该类中定义的方法都所有对象都具备的。
常见方法：
	 1，boolean equals(Object obj):用于比较两个对象是否相同。
	 	Object中的equals方法比较的是地址值是否相同，相当于“==”。
	
	 2，String toString(): 获取对象的字符串表现形式：类名@哈希值  
	 	相当于：getClass().getName()+"@"+Integer.toHexString(hashCode());
	
	 3，Class getClass():获取正在运行的对象所属的字节码文件的对象。
		 也就是说如果Demo d = new Demo(); d.getClass()
		 获取的就是d执行的对象所属的字节码文件Demo.class对象。
		 
*/
public class Demo_Object {
    public static void main(String[] args) {

        Demo demo = new Demo();
        //class com.watayouxiang.androiddemo.java.obj.Demo@14ae5a5
        System.out.println(demo.getClass() + "@" + Integer.toHexString(demo.hashCode()));
        //com.watayouxiang.androiddemo.java.obj.Demo@14ae5a5
        System.out.println(demo);

        TestDemo testDemo = new TestDemo(4);
        //num = 4
        System.out.println(testDemo);

    }

    static class TestDemo {
        private int num;

        TestDemo(int num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TestDemo))
                return false;
            return this.num == ((TestDemo) obj).num;
        }

        @Override
        public String toString() {
            return "num = " + num;
        }
    }

    static class Demo {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        // GC回收时调用
    }
}

