package com.watayouxiang.myjava.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：建立一个文本转换服务器。
 * 客户端给服务端发送文本，服务单会将文本转成大写在返回给客户端。
 * 而且客户度可以不断的进行文本转换。当客户端输入over时，转换结束。
 */
public class Demo04_TcpDemo {

    // ===============================================================================
    // 第一种方式
    // ===============================================================================

    static class Client {
        public static void main(String[] args) throws IOException {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = 10005;
            Socket socket = new Socket(ip, port);

            BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = systemReader.readLine()) != null) {
                if ("over".equals(line))
                    break;
                writer.write(line);//写数据到缓冲区
                writer.newLine();//发送结束标记
                writer.flush();//刷新缓冲区数据
                System.out.println("发送 -> " + line);
                String upperCase = reader.readLine();
                System.out.println("收到 -> " + upperCase);
            }

            systemReader.close();
            socket.close();
        }
    }

    static class Server {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(10005);
            Socket socket = serverSocket.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "...connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("收到 -> " + line);
                String upperCase = line.toUpperCase();
                writer.write(upperCase);//发送数据到缓冲区
                writer.newLine();//发送结束标记
                writer.flush();//刷新缓冲区
                System.out.println("发送 -> " + upperCase);
            }

            socket.close();
            serverSocket.close();
        }
    }

    // ===============================================================================
    // 第二种方式
    // ===============================================================================

    static class Client2 {
        public static void main(String[] args) throws IOException {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = 10005;
            Socket socket = new Socket(ip, port);

            BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);//自动刷新缓冲区
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = systemReader.readLine()) != null) {
                if ("over".equals(line))
                    break;
                writer.println(line);//发送数据 + 换行(结束标记)
                System.out.println("发送 -> " + line);
                String upperCase = reader.readLine();
                System.out.println("收到 -> " + upperCase);
            }

            systemReader.close();
            socket.close();
        }
    }

    static class Server2 {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(10005);
            Socket socket = serverSocket.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "...connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);//自动刷新缓冲区
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("收到 -> " + line);
                String upperCase = line.toUpperCase();
                writer.println(upperCase);//发送数据 + 换行(结束标记)
                System.out.println("发送 -> " + upperCase);
            }

            socket.close();
            serverSocket.close();
        }
    }
}
