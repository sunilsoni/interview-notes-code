package com.interview.notes.code.year.y2024.march24.test13;

public class MyClass {
    static int staticVariable = initializeStaticVariable();

    static {
        System.out.println("Static block 1");
    }

    static {
        System.out.println("Static block 2");
    }

    static int initializeStaticVariable() {
        System.out.println("Initializing static variable");
        return 10;
    }

    static void staticMethod() {
        System.out.println("Static method");
    }

    public static void main(String[] args) {
        System.out.println("Main method");
        MyClass.staticMethod();
        // MyClass.instanceMethod(); // This would cause a compilation error
    }

    void instanceMethod() {
        System.out.println("Instance method");
    }
}
