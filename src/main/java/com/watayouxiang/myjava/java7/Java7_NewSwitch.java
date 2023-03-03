package com.watayouxiang.myjava.java7;

public class Java7_NewSwitch {

    /**
     * 新版switch语句
     */
    public static void main(String[] args) {
        String sex = "男";

        switch (sex) {//sex.hasCode()
            case "男"://"男".hasCode()
                System.out.println("先生，你好！");
                break;
            case "女"://"男".hasCode()
                System.out.println("女士，你好！");
                break;
            default:
                System.out.println("天呐，你是谁！");
                break;
        }
    }
}
