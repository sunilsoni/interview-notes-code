package com.interview.notes.code.test.test10;

public class A {
    private static boolean isFirstInstance = true;

    public A() {
        if (isFirstInstance) {
            isFirstInstance = false;
            System.out.println("Creating instance of A");
        } else {
            System.out.println("Creating instance of A failed");
        }
    }
}


