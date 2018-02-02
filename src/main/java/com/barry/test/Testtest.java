package com.barry.test;

/**
 * Created by Barry on 2018/1/29.
 */
public class Testtest {
    public static String test() {
        String s = "qwe qww     qqq ";

        String sss = null;
        int num = 0;
        for ( String ss : s.split("\\s+")) {
            sss = ss;
            num++;
            System.out.println(sss + " " +num);
        }
        return sss;
    }
}
