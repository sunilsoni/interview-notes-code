package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.Arrays;
import java.util.List;

public class EvenSumExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int evenSum = numbers.stream()
                             .filter(n -> n % 2 == 0) // Keep only even numbers
                             .mapToInt(Integer::intValue) // Convert to IntStream
                             .sum(); // Sum all even numbers

        System.out.println("Sum of even numbers: " + evenSum); // Output: 12
    }
}
