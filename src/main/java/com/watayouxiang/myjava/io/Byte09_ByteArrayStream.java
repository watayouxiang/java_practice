package com.watayouxiang.myjava.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*

ByteArrayInputStream 和 ByteArrayOutputStream：

用于操作字节数组的流对象。
ByteArrayInputStream：在构造的时候，需要接收数据源，而且数据源是一个字节数组。
ByteArrayOutputStream：在构造的时候，不用定义数据目的，因为该对象中已经内部封装了可变长度的字节数组，这就是数据目的地。
因为这两个流对象都操作的数组，并没有使用系统资源。所以，不用进行close关闭流。

方法：
writeTo(OutputStream out)：将此 byte 数组输出流的全部内容写入到指定的输出流参数中，
                          这与使用 out.write(buf, 0, count) 调用该输出流的 write 方法效果一样。
size()：返回缓冲区的当前大小

----------------------------

源设备：
键盘 System.in，硬盘 FileStream，内存 ArrayStream。

目的设备：
控制台 System.out，硬盘FileStream，内存 ArrayStream。

 */
public class Byte09_ByteArrayStream {
    public static void main(String[] args) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream("hello tao, test file.".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int by;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }

        System.out.println(bos.size());
        System.out.println(bos.toString());

        bos.writeTo(new FileOutputStream("test_ByteArrayStream.txt"));
    }
}