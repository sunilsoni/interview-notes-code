package com.interview.notes.code.months.june24.test8;

interface MyInterface {
    // Abstract method
    void normalMethod();

    // Default method
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
}