package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.Arrays;
import java.util.List;

public class EvenSumExample {
    public static void main(String[] args) {
        // Step 1: Create the list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Step 2: Use Stream API to filter even numbers and sum them
        int sumOfEven = numbers.stream()              // Convert list to Stream
                .filter(n -> n % 2 == 0) // Keep only even numbers
                .mapToInt(Integer::intValue) // Convert to IntStream
                .sum();                   // Sum all even numbers

        // Step 3: Print result
        System.out.println("Sum of even numbers: " + sumOfEven);
    }
}