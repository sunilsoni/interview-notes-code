package com.interview.notes.code.year.y2024.sept24.test4;

@FunctionalInterface
public interface Tester<A, B, C> {
    static void printBase() {  // Static method
        System.out.println("I'm Base Tester");
    }

    C apply(A a, B b);  // Abstract method

    default void print() {     // Default method
        System.out.println("I'm Tester");
    }
}
