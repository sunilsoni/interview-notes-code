package com.interview.notes.code.year.y2023.june23.test10;

public class ClassA {
    private static boolean isFirstInstance = true;

    public ClassA() {
        if (isFirstInstance) {
            isFirstInstance = false;
            System.out.println("Creating instance of A");
        } else {
            System.out.println("Creating instance of A failed");
        }
    }
}


