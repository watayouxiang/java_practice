package com.watayouxiang.myjava.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 需求：写一个聊天程序
 * <p>
 * 起两个线程，一个发送端线程，一个接收端线程。
 * 发送端等待键盘输入，将输入内容发送给接收端。
 */
class Demo02_Udp {

    public static void main(String[] args) throws Exception {
        new Thread(new SendThread(new DatagramSocket())).start();
        new Thread(new ReceiveThread(new DatagramSocket(10002))).start();
    }

    /**
     * udp接收端线程
     */
    static class ReceiveThread implements Runnable {
        private DatagramSocket socket;

        ReceiveThread(DatagramSocket ds) {
            this.socket = ds;
        }

        public void run() {
            try {
                while (true) {
                    //定义数据包用于存储数据
                    byte[] buf = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    //将接收到的数据存入数据包
                    //DatagramPacket.receive(DatagramPacket)是阻塞式方法
                    socket.receive(packet);
                    //获取数据包中的数据
                    String ip = packet.getAddress().getHostAddress();
                    String data = new String(packet.getData(), 0, packet.getLength());
                    int port = packet.getPort();
                    System.out.println(ip + ":" + port + ":" + " 收到 -> " + data);
                    System.out.println();
                }
            } catch (Exception e) {
                throw new RuntimeException("接收端失败");
            } finally {
                //关闭资源
                socket.close();
            }
        }
    }

    /**
     * udp发送端线程
     */
    static class SendThread implements Runnable {
        private DatagramSocket socket;

        SendThread(DatagramSocket ds) {
            this.socket = ds;
        }

        public void run() {
            try {
                BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
                String line;
                //BufferedReader.readLine()是阻塞式方法
                while ((line = systemIn.readLine()) != null) {
                    //当键盘键入"over"时，代表数据键入结束
                    if ("over".equals(line)) {
                        break;
                    }
                    //封装成数据包
                    byte[] buf = line.getBytes();
                    DatagramPacket packet = new DatagramPacket(
                            buf,//包数据
                            buf.length,//包长度
                            InetAddress.getLocalHost(),//目的地址
                            10002//目的端口号
                    );
                    //将数据发送出去
                    socket.send(packet);
                    System.out.println("发送 -> " + line);
                }
            } catch (Exception e) {
                throw new RuntimeException("发送端失败");
            } finally {
                //关闭资源
                socket.close();
            }
        }
    }

}
