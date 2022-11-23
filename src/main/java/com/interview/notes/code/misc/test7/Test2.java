package com.interview.notes.code.misc.test7;

public class Test2 {
    public static void main(String[] args) {
        call('c');
    }

    public static void call(int a) {
        System.out.println("int...");
    }

    public static void call(char a) {
        System.out.println("char...");
    }

    public static void call(Integer a) {
        System.out.println("Integer...");
    }

}
