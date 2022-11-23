package com.interview.notes.code.misc;

public class JavaClass {
    public static void main(String[] args) {

        int x = 2;
        x = ~(x << 1);
        System.out.println(x);
        //meth(args);
    }


    public static void meth(String[] arg) {
        System.out.println(java.util.Arrays.toString(arg));
    }
}