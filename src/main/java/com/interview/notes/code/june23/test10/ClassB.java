package com.interview.notes.code.june23.test10;

public class ClassB extends ClassA {
    private static boolean isFirstInstance = true;

    public ClassB() {
        if (isFirstInstance) {
            isFirstInstance = false;
            System.out.println("Creating instance of B");
        } else {
            System.out.println("Creating instance of B failed");
        }
    }
}