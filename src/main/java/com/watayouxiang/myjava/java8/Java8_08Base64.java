package com.watayouxiang.myjava.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/*

在Java 8中，Base64编码已经成为Java类库的标准。
Java 8 内置了 Base64 编码的编码器和解码器。
Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：

    基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
    URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
    MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。

 */
public class Java8_08Base64 {
    public static void main(String[] args) {
        byte[] mimeBytes = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
        byte[] stringBytes = "runoob?java8".getBytes(StandardCharsets.UTF_8);

        // 编码（基本）
        String baseEncodedString = Base64.getEncoder().encodeToString(stringBytes);
        System.out.println("Base64 编码 (基本) :" + baseEncodedString);
        System.out.println("Base64 解码 (基本) " + new String(Base64.getDecoder().decode(baseEncodedString), StandardCharsets.UTF_8));
        System.out.println();

        // 编码（URL）
        String urlEncodedString = Base64.getUrlEncoder().encodeToString(stringBytes);
        System.out.println("Base64 编码 (URL) :" + urlEncodedString);
        System.out.println("Base64 解码 (URL) :" + new String(Base64.getUrlDecoder().decode(urlEncodedString), StandardCharsets.UTF_8));
        System.out.println();

        // 编码（MIME）
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码 (MIME) :" + mimeEncodedString);
        System.out.println("Base64 解码 (MIME) :" + new String(Base64.getMimeDecoder().decode(mimeEncodedString), StandardCharsets.UTF_8));
    }
}
