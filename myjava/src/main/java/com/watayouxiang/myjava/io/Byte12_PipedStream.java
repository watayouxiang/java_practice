package com.watayouxiang.myjava.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
 * 管道流：PipedInputStream和PipedOutputStream
 *
 * 用于將管道输入流和管道输出流相连接。
 * 注意：不建议对这两个对象尝试使用单个线程，因为这样可能会造成该线程死锁。
 * 所以使用多线程。
 *
 * 构造方法：
 * PipedInputStream(PipedOutputStream src) ：创建 PipedInputStream，使其连接到管道输出流 src。
 * PipedInputStream() ：创建尚未连接的 PipedInputStream。
 *
 */
public class Byte12_PipedStream {

    public static void main(String[] args) throws IOException {
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        inputStream.connect(outputStream);

        new Thread(new MyReader(inputStream)).start();
        new Thread(new MyWriter(outputStream)).start();
    }

    private static class MyWriter implements Runnable {
        private final PipedOutputStream outputStream;

        private MyWriter(PipedOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                System.out.println("before write(), 5 second start write...");
                Thread.sleep(5000);

                outputStream.write("[ I am coming ]".getBytes());
                System.out.println("end write()");

                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyReader implements Runnable {
        private final PipedInputStream inputStream;

        private MyReader(PipedInputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                byte[] buf = new byte[1024];

                //该方法阻塞直到输入数据可用
                System.out.println("before read()");
                int len = inputStream.read(buf);
                System.out.println("end read()");

                String txt = new String(buf, 0, len);
                System.out.println(txt);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
