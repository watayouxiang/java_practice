package com.watayouxiang.myjava.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：上传文件
 */
public class Demo06_TcpDemo3 {

    /**
     * 客户端
     */
    static class Client {
        public static void main(String[] args) throws IOException {
            Socket s = new Socket("127.0.0.1", 10007);

            // 向服务器写数据
            File file = new File("README.md");
            FileInputStream fis = new FileInputStream(file);

            OutputStream out = s.getOutputStream();

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }

            s.shutdownOutput();// 结束标记

            // 获取服务器回执
            InputStream in = s.getInputStream();

            byte[] bufIn = new byte[1024];
            int lenIn = in.read(bufIn);
            String info = new String(bufIn, 0, lenIn);

            System.out.println(info);

            // 释放资源
            fis.close();
            s.close();
        }
    }

    /**
     * 服务端
     */
    static class Server {
        public static void main(String[] args) throws IOException {
            ServerSocket ss = new ServerSocket(10007);

            while (true) {
                Socket s = ss.accept();
                new Thread(new ServerThread(s)).start();
            }
        }
    }

    static class ServerThread implements Runnable {
        private Socket s;

        ServerThread(Socket s) {
            this.s = s;
        }

        @Override
        public void run() {
            int count = 1;
            String ip = s.getInetAddress().getHostAddress();

            try {
                System.out.println(ip + "...connected");

                File file = new File(ip + "(" + (count) + ")" + ".md");

                while (file.exists())
                    file = new File(ip + "(" + (count++) + ")" + ".md");

                // 接收客户端数据
                InputStream in = s.getInputStream();

                FileOutputStream fos = new FileOutputStream(file);

                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }

                // 告诉客户端接收成功
                OutputStream out = s.getOutputStream();
                byte[] info = "上传成功".getBytes();
                out.write(info);

                // 释放资源
                fos.close();
                s.close();
            } catch (Exception e) {
                throw new RuntimeException(ip + "上传失败");
            }
        }
    }

}