package com.interview.notes.code.year.y2024.jan24.test3;

interface InterfaceA {
    default void doSomething() {
        System.out.println("InterfaceA's doSomething()");
    }
}

interface InterfaceB {
    default void doSomething() {
        System.out.println("InterfaceB's doSomething()");
    }
}

class MyClass implements InterfaceA, InterfaceB {
    // You must provide your own implementation of doSomething
    @Override
    public void doSomething() {
        // Call one of the interface's default methods explicitly
        InterfaceA.super.doSomething(); // Call InterfaceA's doSomething()
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.doSomething(); // Calls InterfaceA's doSomething()
    }
}
