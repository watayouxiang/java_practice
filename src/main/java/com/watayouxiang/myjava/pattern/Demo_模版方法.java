package com.watayouxiang.myjava.pattern;

/*
模版方法设计模式：

需求：获取一段程序运行的时间。

什么是模版方法设计模式：
	在定义功能时，功能的一部分是确定的，但是有一部分是不确定，而确定的部分在使用不确定的部分，
	那么这时就将不确定的部分暴露出去。由该类的子类去完成。

*/
public class Demo_模版方法 {

    public static void main(String[] args) {
        SubTime st = new SubTime();
        st.getTime();
    }


    static class SubTime extends GetTime {
        public void runCode() {
            for (int x = 0; x < 4000; x++) {
                System.out.println(x);
            }
        }
    }

    static abstract class GetTime {
        final void getTime() {
            long start = System.currentTimeMillis();
            runCode();
            long end = System.currentTimeMillis();
            System.out.println("毫秒：" + (end - start));
        }

        // 只有runCode方法是不确定的
        public abstract void runCode();
    }

}