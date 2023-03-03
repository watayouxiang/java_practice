package com.watayouxiang.myjava.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Demo01_InetAddress {

    public static void main(String[] args) throws UnknownHostException {
        getLocalHostInfo();
        getInetAddress();
    }

    private static void getInetAddress() throws UnknownHostException {
        //根据主机名获取 InetAddress
        InetAddress ia = InetAddress.getByName("TaoWang-MacBook-Pro.local");
        System.out.println(ia.toString());
        //根据主机名获取所有的 InetAddress
        InetAddress[] ias = InetAddress.getAllByName("www.baidu.com");
        System.out.println(Arrays.toString(ias));
    }

    private static void getLocalHostInfo() throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia.toString());//TaoWang-MacBook-Pro.local/192.168.123.26
        //获取IP地址
        System.out.println(ia.getHostAddress());//192.168.123.26
        //获取主机名
        System.out.println(ia.getHostName());//TaoWang-MacBook-Pro.local
    }
}
