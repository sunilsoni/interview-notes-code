package com.interview.notes.code.year.y2025.august.oracle.test9;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionalDemo {
    public static void main(String[] args) {
        int number = 10;

        // Consumer to print the number
        Consumer<Integer> printer = n -> System.out.println("Number is: " + n);

        // Predicate to check if number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Apply both
        printer.accept(number);
        System.out.println("Is even? " + isEven.test(number));
    }
}
