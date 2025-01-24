package com.interview.notes.code.year.y2025.jan24.test14;

public class OverloadingExample {
    public void print(String s) {
        System.out.println("String method called");
    }

    public void print(Object o) {
        System.out.println("Object method called");
    }

    public void print(Integer i) {
        System.out.println("Integer method called");
    }

    public static void main(String[] args) {
        OverloadingExample example = new OverloadingExample();
        //example.print(null); // This will cause a compile-time error
    }
}
