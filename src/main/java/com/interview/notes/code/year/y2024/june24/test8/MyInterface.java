package com.interview.notes.code.year.y2024.june24.test8;

interface MyInterface {
    // Abstract method
    void normalMethod();

    // Default method
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
}