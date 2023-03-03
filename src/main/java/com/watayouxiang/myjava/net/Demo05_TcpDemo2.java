package com.watayouxiang.myjava.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过网络，客户端向服务端发送一个文件
 */
public class Demo05_TcpDemo2 {

    static class Client {
        public static void main(String[] args) throws IOException {
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 10006);

            BufferedReader fileReader = new BufferedReader(new FileReader("README.md"));
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = fileReader.readLine()) != null) {
                socketWriter.println(line);
            }
            socket.shutdownOutput();//仅关闭客户端的输出流，相当于给流中加入一个结束标记-1

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("收到 -> " + socketReader.readLine());

            //关闭资源
            fileReader.close();
            socket.close();
        }
    }

    static class Server {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(10006);
            Socket socket = serverSocket.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "...connected");

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter fileWriter = new PrintWriter(new FileWriter("README2.md"));
            String line;
            while ((line = socketReader.readLine()) != null) {
                fileWriter.println(line);
            }

            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
            socketWriter.println("上传成功");

            fileWriter.close();
            socket.close();
            serverSocket.close();
        }
    }

}
