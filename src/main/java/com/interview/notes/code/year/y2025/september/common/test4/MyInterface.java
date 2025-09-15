package com.interview.notes.code.year.y2025.september.common.test4;

public interface MyInterface {
    static void info() {
        System.out.println("Static method in interface");
    }

    default void greet() {
        System.out.println("Hello from default method");
    }
}
