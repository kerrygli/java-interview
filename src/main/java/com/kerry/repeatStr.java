package com.kerry;

public class repeatStr {


    public static void main(String[] args) {


        String str = "abc";

        System.out.println(repeatStr(str));

    }

    public static boolean repeatStr(String s) {
        String newStr = s+s;
        return newStr.substring(1, newStr.length() - 1).contains(s);
    }
}
