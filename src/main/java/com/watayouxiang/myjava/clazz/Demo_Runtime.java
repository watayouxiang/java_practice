package com.watayouxiang.myjava.clazz;

import java.io.IOException;

public class Demo_Runtime {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        Process process = null;
        try {
            process = runtime.exec("notepad.exe  Demo_System.java");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (process != null) {
//            process.destroy();
//        }
    }

}
