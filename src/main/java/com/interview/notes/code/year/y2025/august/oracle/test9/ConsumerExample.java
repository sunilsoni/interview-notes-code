package com.interview.notes.code.year.y2025.august.oracle.test9;

import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        int number = 10;

        // Consumer to print the number
        Consumer<Integer> printNumber = n -> System.out.println("Input number is: " + n);

        // Consumer to check and print even/odd
        Consumer<Integer> checkEven = n -> {
            if (n % 2 == 0) {
                System.out.println(n + " is Even");
            } else {
                System.out.println(n + " is Odd");
            }
        };

        // Execute consumers
        printNumber.andThen(checkEven).accept(number);
    }
}
