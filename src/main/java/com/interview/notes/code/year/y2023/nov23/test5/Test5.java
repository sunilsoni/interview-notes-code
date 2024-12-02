package com.interview.notes.code.year.y2023.nov23.test5;

public class Test5 {
    public static void print(Object o) {
        System.out.println("Object method called");
    }

    public static void print(String s) {
        System.out.println("String method called");
    }

    public static void main(String[] args) {
        print(null);
    }

    public static void print1(Integer s) {
        System.out.println("Integer method called");
    }
}
