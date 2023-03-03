package com.watayouxiang.myjava.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/*

Reader
--| FileReader
--|--| LineNumberReader 

 */
public class Char09_LineNumberReader {

    public static void main(String[] args) throws IOException {

        LineNumberReader lnr = new LineNumberReader(new FileReader(new File("README.md")));

        String line;
        // 设置当前行号
        lnr.setLineNumber(0);
        while ((line = lnr.readLine()) != null) {
            // 获得当前行号
            int lineNumber = lnr.getLineNumber();
            System.out.println(lineNumber + "\t" + line);
        }

        lnr.close();

    }

}
