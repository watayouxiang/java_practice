package com.watayouxiang.myjava.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用 ThreadLocal 解决线程安全问题
 */
public class ThreadLocalNormalUsage05 {

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
//        String s;
//        synchronized (ThreadLocalNormalUsage05.class) {
//            s = format.format(date);
//        }
//        return s;
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return  dateFormat.format(date);

    }

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
//    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        // 相比要创建1000个SimpleDateFormat对象，这里只需要创建10个对象
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println("第" + finalI + "个: " + date);
                }
            });
        }
        threadPool.shutdown();
    }

}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

}
