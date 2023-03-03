package com.watayouxiang.myjava.grammar;

/*
1.进制转换常识

    比特 bit
    字节 byte
    8 bit = 1 byte

    八进制是以0标示：比如0125，0代表八进制，125代表值
    十六进制以0x标示：比如0x55，0x代表十六进制，55代表值

    十进制 --> 二进制
    二进制 --> 十进制
    二进制 --> 八进制
    二进制 --> 十六进制

2.负数二进制表示

    负数二进制是正数二进制取反加一

    例如-6的表示：
        0000 0110
    ～  1111 1001
    +	0000 0001
    ----------------
        1111 1010

 */
public class Demo_进制转换 {

    public static void main(String[] args) {
//        toBin(6);// 十进制-->二进制
//        toHex(-6);// 十进制-->十六进制
//        trans(-6, 1, 1);// 十进制-->二进制
//        trans(-6, 7, 3);// 十进制-->八进制
//        trans(-6, 15, 4);// 十进制-->十六进制
    }

    /**
     * 进制转换器
     *
     * @param num    十进制数
     * @param base   转二进制则base=1，转八进制则base=7，转十六进制则base=15
     * @param offset 二进制是2的1次方，八进制是2的3次方，十六进制是2的4次方
     */
    private static void trans(int num, int base, int offset) {
        if (num == 0) {
            System.out.println(0);
            return;
        }
        char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder buffer = new StringBuilder();
        while (num != 0) {
            int temp = num & base;
            buffer.append(chs[temp]);
            num = num >>> offset;
        }
        System.out.println(buffer.reverse());
    }

    /**
     * 十进制-->十六进制
     *
     * @param num 十进制数（正负数均可）
     */
    private static void toHex(int num) {
        System.out.println("标准答案：" + Integer.toHexString(num));
        StringBuilder buffer = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            int temp = num & 15;
            if (temp > 9) {
                buffer.append((char) (temp - 10 + 'a'));
            } else {
                buffer.append(temp);
            }
            num = num >>> 4;
        }
        System.out.println("我的答案：" + buffer.reverse());
    }

    /**
     * 十进制-->二进制, 除2取模
     *
     * @param num 十进制数（必须为正数）
     */
    private static void toBin(int num) {
        System.out.println("标准答案：" + Integer.toBinaryString(num));
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            builder.append(num % 2);
            num = num / 2;
        }
        System.out.println("我的答案：" + builder.reverse());
    }
}