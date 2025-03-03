package com.interview.notes.code.year.y2025.feb.common.test6;

import java.util.Arrays;
import java.util.List;

public class EvenNumberCount {
    public static void main(String[] args) {
        // Sample list of numeric strings
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");

        // Use stream to convert strings to integers, filter even numbers, and count them
        long evenCount = numbers.stream()
                .map(Integer::parseInt)       // Convert each string to an Integer
                .filter(num -> num % 2 == 0)    // Keep only even numbers
                .count();                     // Count the remaining numbers

        System.out.println("Count of even numbers: " + evenCount);
    }
}