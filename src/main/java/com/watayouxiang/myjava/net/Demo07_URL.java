package com.watayouxiang.myjava.net;

import java.net.MalformedURLException;
import java.net.URL;

public class Demo07_URL {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://www.baidu.com/s?wd=hello");

        // 协议：https
        System.out.println("getProtocol(): " + url.getProtocol());
        // 主机名：www.baidu.com
        System.out.println("getHost(): " + url.getHost());
        // 端口号：-1（如果没有则为-1）
        System.out.println("getPort(): " + url.getPort());
        // 路径：/s
        System.out.println("getPath(): " + url.getPath());
        // 文件：/s?wd=hello
        System.out.println("getFile(): " + url.getFile());
        // 查询：wd=hello
        System.out.println("getQuery(): " + url.getQuery());
    }

}
