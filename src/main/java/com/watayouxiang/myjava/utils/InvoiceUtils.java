package com.watayouxiang.myjava.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发票工具
 */
public class InvoiceUtils {

    /**
     * 统计文件夹中的发票总金额
     * <p>
     * 遍历目录，该目录下没有子目录，内含如下文件：
     * 60.ofd
     * 68.14.pdf
     * 69.pdf
     * 74.2.png
     * 76.9.pdf
     * 200-.jpg
     * 221.9.pdf
     */
    public static void calTotalAmount(String pathname) {
        File file = new File(pathname);
        if (!file.isDirectory()) {
            throw new RuntimeException(file.getAbsolutePath() + "：不是目录");
        }
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        if (files == null) {
            throw new RuntimeException(file.getAbsolutePath() + "：无法访问");
        }
        if (files.length == 0) {
            throw new RuntimeException(file.getAbsolutePath() + "：没有文件");
        }
        double total = 0;
        int count = 0;
        for (File f : files) {
            // 60.ofd
            // 68.14.pdf
            // 69.pdf
            // 74.2.png
            // 76.9.pdf
            // 200-.jpg
            // 221.9.pdf
            String name = f.getName();
            Matcher matcher = Pattern.compile("(^\\d+\\.\\d+)|(^\\d+)").matcher(name);
            if (!matcher.find()) {
                throw new RuntimeException(name + "：该发票无金额");
            }
            String group = matcher.group();
            double cny = Double.parseDouble(group);
            total += cny;
            count++;
            // System.out.println(name + " --> " + cny);
        }
        System.out.println(count + "张发票，总金额为：" + total + "元");
    }

    public static void main(String[] args) {
        InvoiceUtils.calTotalAmount("/Users/wangtao/Documents/wata_钛特云科技资料/发票/未报销/202203发票");
    }
}
