package com.interview.notes.code.year.y2023.nov23.test4;

// Define a functional interface with one abstract method
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        // Create a lambda expression for addition
        MathOperation addition = (a, b) -> a + b;

        // Use the lambda expression to perform addition
        int result = addition.operate(5, 3);
        System.out.println("Result of addition: " + result);
    }
}
