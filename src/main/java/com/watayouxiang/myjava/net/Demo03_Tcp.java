package com.watayouxiang.myjava.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP传输：用到的类是Socket和ServerSocket
 * <p>
 * TCP传输是面向连接的，所以无论先启动客户端还是服务端，当两端未连接上之前都会阻塞等待
 */
public class Demo03_Tcp {

    /**
     * tcp客户端：发送数据给服务端，并获取服务端的反馈
     */
    static class Client {
        public static void main(String[] args) throws IOException {
            //1，建立客户端，指定ip地址是本机地址，端口号是10003
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 10003);
            //2，要获取Socket中的输出流，发数据
            String sendData = "服务端你好";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(sendData.getBytes());
            System.out.println("发送 -> " + sendData);
            //3，获取Socket中的输入流，获取服务端的数据
            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            System.out.println("收到 -> " + new String(buf, 0, len));
            //4，关闭客户端口
            socket.close();
        }
    }

    /**
     * tcp服务端：接收客户端数据，并给予反馈
     */
    static class Server {
        public static void main(String[] args) throws IOException {
            //1，建立服务器端ServerSocket服务，并监听一个端口
            ServerSocket serverSocket = new ServerSocket(10003);
            //2，通过accept方法获取连接过来的对象
            Socket socket = serverSocket.accept();
            //3，打印出连接到服务器端的客户端ip
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "...connected");
            //4，获取客户端发过来的数据
            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            System.out.println("收到 -> " + new String(buf, 0, len));
            //5，给客户端发送反馈信息
            String sendData = "客户端好";
            OutputStream out = socket.getOutputStream();
            out.write(sendData.getBytes());
            System.out.println("发送 -> " + sendData);
            //6，关闭客户端
            socket.close();
            //7，关闭服务器端
            serverSocket.close();
        }
    }
}
