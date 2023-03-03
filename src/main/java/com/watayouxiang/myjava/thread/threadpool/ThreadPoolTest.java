package com.watayouxiang.myjava.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

    private static ThreadPoolExecutor executorService = null;
//    private static SendTask sendTask = null;
//    private static ReceiveTask receiveTask = null;

    public static void main(String[] args) {
//        for (int i = 0; i < 2000; i++) {
//            connect(String.valueOf(i));
//            disconnect();
//        }

        connect(String.valueOf(1));
        connect(String.valueOf(2));
        disconnect();
    }

    private static void disconnect() {
//        sendTask.setCloseTag(true);
//        receiveTask.setCloseTag(true);
        executorService.shutdownNow();
    }

    private static void connect(String tag) {
        executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.execute(/*sendTask = */new SendTask(tag + " ----- 线程1"));
        executorService.execute(/*receiveTask = */new ReceiveTask(tag + " ----- 线程2"));
    }

    public static class ReceiveTask extends Thread {
        private final String name;
        private boolean closeTag = false;

        public ReceiveTask(String name) {
            super(name);
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (; !Thread.interrupted(); ) {
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                if (!closeTag) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " end");
        }

        public void setCloseTag(boolean closeTag) {
            this.closeTag = closeTag;
        }
    }

    public static class SendTask extends Thread {
        private final LinkedBlockingQueue<String> mPacketQueue = new LinkedBlockingQueue<>();
        private final String name;
        private boolean closeTag = false;

        public SendTask(String name) {
            super(name);
            this.name = name;
            put("消息1");
            put("消息2");
            put("消息3");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String packet = mPacketQueue.take();
                }
            } catch (Exception e) {
                if (!closeTag) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " end");
        }

        public void put(String str) {
            try {
                mPacketQueue.put(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void setCloseTag(boolean closeTag) {
            this.closeTag = closeTag;
        }
    }
}
