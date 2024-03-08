package com.interview.notes.code.months.march24.test6;

// Functional interface with two parameters
@FunctionalInterface
interface TwoArgFunction<T, U, R> {
    R apply(T arg1, U arg2);
}

public class Main {
    public static void main(String[] args) {
        // Define a lambda expression to add two integers
        TwoArgFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        
        // Test the lambda expression
        int result = add.apply(3, 5);
        System.out.println("Result of addition: " + result); // Output: Result of addition: 8
    }
}
