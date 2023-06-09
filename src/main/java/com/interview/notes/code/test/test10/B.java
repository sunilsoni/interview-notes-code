package com.interview.notes.code.test.test10;

public class B extends A {
    private static boolean isFirstInstance = true;

    public B() {
        if (isFirstInstance) {
            isFirstInstance = false;
            System.out.println("Creating instance of B");
        } else {
            System.out.println("Creating instance of B failed");
        }
    }
}