package com.interview.notes.code.months.year2023.dec23.test5;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        // Using lambda expressions to implement the custom functional interface

        // Addition operation
        MathOperation addition = (a, b) -> a + b;

        // Subtraction operation
        MathOperation subtraction = (a, b) -> a - b;

        // Multiplication operation
        MathOperation multiplication = (a, b) -> a * b;

        // Division operation (handle division by zero)
        MathOperation division = (a, b) -> {
            if (b != 0) {
                return a / b;
            } else {
                throw new ArithmeticException("Division by zero");
            }
        };

        // Using the lambda expressions to perform calculations
        System.out.println("Addition: " + addition.operate(10, 5));           // Output: 15
        System.out.println("Subtraction: " + subtraction.operate(10, 5));     // Output: 5
        System.out.println("Multiplication: " + multiplication.operate(10, 5)); // Output: 50
        System.out.println("Division: " + division.operate(10, 5));           // Output: 2
    }
}
