package com.interview.notes.code.year.y2026.feb.common.test10;

// Thread-safe Singleton using Enum
public enum MySingleton {
    INSTANCE; // only one instance created

    public static void main(String[] args) {
        MySingleton s1 = MySingleton.INSTANCE;
        MySingleton s2 = MySingleton.INSTANCE;

        s1.showMessage();

        if (s1 == s2) {
            System.out.println("PASS: Only one instance exists.");
        } else {
            System.out.println("FAIL: Multiple instances created.");
        }
    }

    public void showMessage() {
        System.out.println("Hello from Enum Singleton!");
    }
}



