package com.interview.notes.code.months.year2023.Oct23.test3;

public class CalculatorExample {

    public static void main(String[] args) {
        // Using a lambda expression to implement the Calculator interface
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        Calculator multiplication = (a, b) -> a * b;
        Calculator division = (a, b) -> {
            if (b != 0) {
                return a / b;
            } else {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
        };

        // Using the calculator functions
        int result1 = calculate(10, 5, addition);
        int result2 = calculate(10, 5, subtraction);
        int result3 = calculate(10, 5, multiplication);
        int result4 = calculate(10, 5, division);

        System.out.println("Addition: " + result1);
        System.out.println("Subtraction: " + result2);
        System.out.println("Multiplication: " + result3);
        System.out.println("Division: " + result4);
    }

    public static int calculate(int a, int b, Calculator calculator) {
        return calculator.calculate(a, b);
    }
}
