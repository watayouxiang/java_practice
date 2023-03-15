package com.watayouxiang.myjava.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
请求头如下：

========================================
GET / HTTP/1.1
Host: 127.0.0.1:11000
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng;q=0.8,application/signed-exchange;v=b3
Sec-Fetch-Site: cross-site
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9


========================================
*/
public class Demo09_HttpDemo {

    /**
     * 模拟浏览器：发送数据给Tomcat服务器
     */
    static class Browser {
        public static void main(String[] args) throws IOException {
            String url = "http://127.0.0.1:8080/myweb/demo.html";

            // 获取 "主机"、"端口号"、"路径"
            int index1 = url.indexOf("//") + 2;
            int index2 = url.indexOf("/", index1);
            String host_port = url.substring(index1, index2);
            String[] arr = host_port.split(":");

            String host = arr[0];//host
            int port = Integer.parseInt(arr[1]);//port
            String path = url.substring(index2);//path

            // 创建socket连接
            Socket socket = new Socket(host, port);

            // 发送数据
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET " + path + " HTTP/1.1");
            out.println("Accept: */*");
            out.println("Accept-Language: zh-cn");
            out.println("Host: " + host_port);
            out.println("Connection: closed");
            out.println();
            out.println();

            // 接收数据
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.print(line + "\r\n");
            }

            // 释放资源
            socket.close();
        }
    }

    /**
     * 模拟服务器：用Chrome浏览器访问
     */
    static class Server {
        public static void main(String[] args) throws Exception {
            ServerSocket ss = new ServerSocket(11000);

            Socket s = ss.accept();
            String client_ip = s.getInetAddress().getHostAddress();
            System.out.println(client_ip + "...connected");

            // 获取浏览器发送过来的数据
            InputStream in = s.getInputStream();
            System.out.println("========================================");
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            System.out.println(new String(buf, 0, len));
            System.out.println("========================================");

            // 反馈给浏览器
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("客户端你好");

            // 关闭资源
            s.close();
            ss.close();
        }
    }

}
