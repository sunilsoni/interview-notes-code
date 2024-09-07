package com.interview.notes.code.months.sept24.test4;

@FunctionalInterface
public interface Tester<A, B, C> {
    static void printBase() {  // Static method
        System.out.println("I'm Base Tester");
    }

    public C apply(A a, B b);  // Abstract method

    default void print() {     // Default method
        System.out.println("I'm Tester");
    }
}
