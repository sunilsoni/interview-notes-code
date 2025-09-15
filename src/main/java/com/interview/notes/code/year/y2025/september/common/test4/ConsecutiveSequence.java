package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsecutiveSequence {
    // Store numbers in ArrayList for dynamic sizing and easy access
    private final List<Integer> numbers;

    // Constructor to initialize the ArrayList
    public ConsecutiveSequence() {
        // Using ArrayList as it provides O(1) for add operations and dynamic sizing
        this.numbers = new ArrayList<>();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic test
        ConsecutiveSequence test1 = new ConsecutiveSequence();
        test1.AddNumber(1);
        System.out.println("Test 1 Result: " + (test1.LongestConsecutive() == 1 ? "PASS" : "FAIL"));

        // Test Case 2: Consecutive numbers
        ConsecutiveSequence test2 = new ConsecutiveSequence();
        test2.AddNumber(1);
        test2.AddNumber(2);
        test2.AddNumber(3);
        System.out.println("Test 2 Result: " + (test2.LongestConsecutive() == 3 ? "PASS" : "FAIL"));

        // Test Case 3: Non-consecutive numbers
        ConsecutiveSequence test3 = new ConsecutiveSequence();
        test3.AddNumber(1);
        test3.AddNumber(3);
        test3.AddNumber(5);
        System.out.println("Test 3 Result: " + (test3.LongestConsecutive() == 1 ? "PASS" : "FAIL"));

        // Test Case 4: Large data test
        ConsecutiveSequence test4 = new ConsecutiveSequence();
        // Add 100000 random numbers
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            test4.AddNumber(rand.nextInt(1000000));
        }
        System.out.println("Test 4 (Large Data) completed successfully");

        // Test Case 5: Empty sequence
        ConsecutiveSequence test5 = new ConsecutiveSequence();
        System.out.println("Test 5 Result: " + (test5.LongestConsecutive() == 0 ? "PASS" : "FAIL"));
    }

    // Method to add numbers to our list
    public void AddNumber(int num) {
        // Simply add the number to our ArrayList
        numbers.add(num);
    }

    // Method to find longest consecutive sequence
    public int LongestConsecutive() {
        // If list is empty, return 0
        if (numbers.isEmpty()) {
            return 0;
        }

        // Convert list to Set for O(1) lookup time
        Set<Integer> numSet = numbers.stream()
                .collect(Collectors.toSet());

        int longestStreak = 1; // Track the longest sequence found
        int currentStreak = 1; // Track current sequence length

        // Iterate through each number in our set
        for (int num : numSet) {
            // Only start checking if this is the start of a sequence
            // (i.e., no number exists before it)
            if (!numSet.contains(num - 1)) {
                currentStreak = 1;
                int currentNum = num;

                // Keep checking next consecutive number
                while (numSet.contains(currentNum + 1)) {
                    currentStreak++;
                    currentNum++;
                }

                // Update longest streak if current streak is longer
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
