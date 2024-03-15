package com.interview.notes.code.months.march24.test13;

public class MyClass {
    static {
        System.out.println("Static block 1");
    }

    static {
        System.out.println("Static block 2");
    }

    static int staticVariable = initializeStaticVariable();

    static int initializeStaticVariable() {
        System.out.println("Initializing static variable");
        return 10;
    }

    static void staticMethod() {
        System.out.println("Static method");
    }

    void instanceMethod() {
        System.out.println("Instance method");
    }

    public static void main(String[] args) {
        System.out.println("Main method");
        MyClass.staticMethod();
        // MyClass.instanceMethod(); // This would cause a compilation error
    }
}
