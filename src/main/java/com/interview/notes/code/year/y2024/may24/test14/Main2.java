package com.interview.notes.code.year.y2024.may24.test14;

@FunctionalInterface
interface TwoArgFunction<T, U, R> {
    R apply(T arg1, U arg2);
}

public class Main2 {
    public static void main(String[] args) {
        // Define a lambda expression for the TwoArgFunction interface
        TwoArgFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // Test the lambda expression
        int result = add.apply(10, 20);
        System.out.println("Result: " + result); // Output: Result: 30
    }
}
