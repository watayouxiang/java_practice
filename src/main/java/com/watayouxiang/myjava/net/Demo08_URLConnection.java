package com.watayouxiang.myjava.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Demo08_URLConnection {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");

        URLConnection conn = url.openConnection();
        System.out.println(conn);

        InputStream in = conn.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            String line = new String(buf, 0, len);
            System.out.print(line);
        }
    }

}
