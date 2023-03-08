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
     * 统计文件夹中的发票总额
     * <p>
     * 遍历目录，该目录下没有子目录，内含如下发票：
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
            System.out.println(file.getAbsolutePath() + "：没有发票");
        }
        double total = 0;
        int count = 0;
        Pattern pattern = Pattern.compile("(^\\d+\\.\\d+)|(^\\d+)");
        for (File f : files) {
            String name = f.getName();
            Matcher matcher = pattern.matcher(name);
            if (!matcher.find()) {
                System.out.println(f.getAbsolutePath() + "：该发票无金额");
                continue;
            }
            double cny = Double.parseDouble(matcher.group());
            total += cny;
            count++;
            System.out.println(name + " --> " + cny);
        }
        System.out.println(count + "张发票，总金额为：" + total + "元");
    }

    public static void main(String[] args) {
        InvoiceUtils.calTotalAmount("/Users/wangtao/Documents/wata_钛特云科技资料/发票/未报销/2022年3月发票-9046.01元");
    }
}
