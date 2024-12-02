package com.interview.notes.code.year.y2024.april24.test11;

// Usage
public class Main {
    public static void main(String[] args) {
        // Using lambda expression to implement the functional interface
        MyFunctionalInterface obj = () -> System.out.println("Executing myMethod");
        obj.myMethod(); // Output: Executing myMethod

        // Using method reference to implement the functional interface
        MyFunctionalInterface obj2 = Main::someMethod;
        obj2.myMethod(); // Output: Executing myMethod
    }

    static void someMethod() {
        System.out.println("Executing someMethod");
    }
}