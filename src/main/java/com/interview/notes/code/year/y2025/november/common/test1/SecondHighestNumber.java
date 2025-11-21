package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class SecondHighestNumber {

    // Main function that finds second highest number and its frequency
    // Input: array of integers
    // Output: String format "number-frequency"
    public static String findSecondHighestWithFrequency(int[] arr) {

        // Step 1: Convert array to stream and find unique numbers sorted in descending order
        // stream() converts primitive array to IntStream
        // boxed() converts int to Integer (needed for Stream operations)
        // distinct() removes duplicate numbers
        // sorted() with reverseOrder() sorts from highest to lowest
        Optional<Integer> secondHighest = Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(1)  // Skip the highest number, take second one
                .findFirst();  // Get first element (which is second highest)

        // Step 2: If second highest doesn't exist, return error message
        // This handles edge cases like array with only 1 unique number
        if (!secondHighest.isPresent()) {
            return "ERROR";  // No second highest found
        }

        // Step 3: Get the second highest number value
        int secondHighestNum = secondHighest.get();

        // Step 4: Count how many times second highest appears in array
        // stream() converts array to stream
        // filter() keeps only numbers equal to secondHighestNum
        // count() returns total count of matching elements
        long frequency = Arrays.stream(arr)
                .filter(num -> num == secondHighestNum)
                .count();

        // Step 5: Return formatted string "number-frequency"
        // String concatenation with hyphen separator
        return secondHighestNum + "-" + frequency;
    }

    // Main method to test all test cases with PASS/FAIL output
    public static void main(String[] args) {

        // Initialize counters to track test results
        // passCount tracks successful tests
        // failCount tracks failed tests
        int passCount = 0;
        int failCount = 0;

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          SECOND HIGHEST NUMBER - TEST SUITE                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ============ BASIC TEST CASES ============

        // Test 1: Problem example
        // Array: {8,9,8,5,4,8,3,8}
        // Unique sorted desc: [9, 8, 5, 4, 3]
        // Second highest: 8, appears 4 times
        // Expected: "8-4"
        int[] test1 = {8, 9, 8, 5, 4, 8, 3, 8};
        String result1 = findSecondHighestWithFrequency(test1);
        boolean pass1 = result1.equals("8-4");
        System.out.println("Test 1 - Problem Example: " + (pass1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [8, 9, 8, 5, 4, 8, 3, 8]");
        System.out.println("  Expected: 8-4 | Got: " + result1 + "\n");
        passCount += pass1 ? 1 : 0;
        failCount += pass1 ? 0 : 1;

        // Test 2: Simple array with clear second highest
        // Array: {1, 2, 3}
        // Unique sorted desc: [3, 2, 1]
        // Second highest: 2, appears 1 time
        // Expected: "2-1"
        int[] test2 = {1, 2, 3};
        String result2 = findSecondHighestWithFrequency(test2);
        boolean pass2 = result2.equals("2-1");
        System.out.println("Test 2 - Simple Sequence: " + (pass2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [1, 2, 3]");
        System.out.println("  Expected: 2-1 | Got: " + result2 + "\n");
        passCount += pass2 ? 1 : 0;
        failCount += pass2 ? 0 : 1;

        // Test 3: Second highest appears multiple times
        // Array: {5, 5, 5, 10, 10}
        // Unique sorted desc: [10, 5]
        // Second highest: 5, appears 3 times
        // Expected: "5-3"
        int[] test3 = {5, 5, 5, 10, 10};
        String result3 = findSecondHighestWithFrequency(test3);
        boolean pass3 = result3.equals("5-3");
        System.out.println("Test 3 - Multiple Second Highest: " + (pass3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [5, 5, 5, 10, 10]");
        System.out.println("  Expected: 5-3 | Got: " + result3 + "\n");
        passCount += pass3 ? 1 : 0;
        failCount += pass3 ? 0 : 1;

        // Test 4: Negative numbers
        // Array: {-5, -1, -10, 0}
        // Unique sorted desc: [0, -1, -5, -10]
        // Second highest: -1, appears 1 time
        // Expected: "-1-1"
        int[] test4 = {-5, -1, -10, 0};
        String result4 = findSecondHighestWithFrequency(test4);
        boolean pass4 = result4.equals("-1-1");
        System.out.println("Test 4 - Negative Numbers: " + (pass4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [-5, -1, -10, 0]");
        System.out.println("  Expected: -1-1 | Got: " + result4 + "\n");
        passCount += pass4 ? 1 : 0;
        failCount += pass4 ? 0 : 1;

        // ============ EDGE CASES ============

        // Test 5: Only one unique number (no second highest)
        // Array: {5, 5, 5, 5}
        // Unique: [5]
        // Can't find second highest
        // Expected: "ERROR"
        int[] test5 = {5, 5, 5, 5};
        String result5 = findSecondHighestWithFrequency(test5);
        boolean pass5 = result5.equals("ERROR");
        System.out.println("Test 5 - Only One Unique: " + (pass5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [5, 5, 5, 5]");
        System.out.println("  Expected: ERROR | Got: " + result5 + "\n");
        passCount += pass5 ? 1 : 0;
        failCount += pass5 ? 0 : 1;

        // Test 6: Exactly two unique numbers
        // Array: {7, 7, 7, 9}
        // Unique sorted desc: [9, 7]
        // Second highest: 7, appears 3 times
        // Expected: "7-3"
        int[] test6 = {7, 7, 7, 9};
        String result6 = findSecondHighestWithFrequency(test6);
        boolean pass6 = result6.equals("7-3");
        System.out.println("Test 6 - Two Unique Numbers: " + (pass6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [7, 7, 7, 9]");
        System.out.println("  Expected: 7-3 | Got: " + result6 + "\n");
        passCount += pass6 ? 1 : 0;
        failCount += pass6 ? 0 : 1;

        // Test 7: Zero in array
        // Array: {0, 0, 1, 2}
        // Unique sorted desc: [2, 1, 0]
        // Second highest: 1, appears 1 time
        // Expected: "1-1"
        int[] test7 = {0, 0, 1, 2};
        String result7 = findSecondHighestWithFrequency(test7);
        boolean pass7 = result7.equals("1-1");
        System.out.println("Test 7 - With Zero: " + (pass7 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [0, 0, 1, 2]");
        System.out.println("  Expected: 1-1 | Got: " + result7 + "\n");
        passCount += pass7 ? 1 : 0;
        failCount += pass7 ? 0 : 1;

        // ============ LARGE DATA TEST CASES ============

        // Test 8: Large array with 10,000 elements
        // Generate: majority 1000, second 500, rest small numbers
        // This tests performance and correctness with large data
        int[] test8 = new int[10000];
        for (int i = 0; i < 5000; i++) {
            test8[i] = 1000;  // 5000 times: 1000
        }
        for (int i = 5000; i < 8000; i++) {
            test8[i] = 500;   // 3000 times: 500
        }
        for (int i = 8000; i < 10000; i++) {
            test8[i] = 100;   // 2000 times: 100
        }
        String result8 = findSecondHighestWithFrequency(test8);
        boolean pass8 = result8.equals("500-3000");
        System.out.println("Test 8 - Large Array (10K): " + (pass8 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: Array with 10,000 elements");
        System.out.println("  Expected: 500-3000 | Got: " + result8 + "\n");
        passCount += pass8 ? 1 : 0;
        failCount += pass8 ? 0 : 1;

        // Test 9: Very large array with 100,000 elements
        // Generate: diverse numbers repeated many times
        // Tests memory efficiency and algorithm speed
        int[] test9 = new int[100000];
        for (int i = 0; i < 50000; i++) {
            test9[i] = 999;   // 50,000 times: 999
        }
        for (int i = 50000; i < 80000; i++) {
            test9[i] = 888;   // 30,000 times: 888
        }
        for (int i = 80000; i < 100000; i++) {
            test9[i] = 777;   // 20,000 times: 777
        }
        String result9 = findSecondHighestWithFrequency(test9);
        boolean pass9 = result9.equals("888-30000");
        System.out.println("Test 9 - Very Large Array (100K): " + (pass9 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: Array with 100,000 elements");
        System.out.println("  Expected: 888-30000 | Got: " + result9 + "\n");
        passCount += pass9 ? 1 : 0;
        failCount += pass9 ? 0 : 1;

        // Test 10: Large array with all same elements
        // Array: 50,000 elements all = 42
        // Should return ERROR (no second highest)
        int[] test10 = new int[50000];
        for (int i = 0; i < 50000; i++) {
            test10[i] = 42;
        }
        String result10 = findSecondHighestWithFrequency(test10);
        boolean pass10 = result10.equals("ERROR");
        System.out.println("Test 10 - Large Array Same Values (50K): " + (pass10 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 50,000 elements all = 42");
        System.out.println("  Expected: ERROR | Got: " + result10 + "\n");
        passCount += pass10 ? 1 : 0;
        failCount += pass10 ? 0 : 1;

        // Test 11: Large array with wide range of numbers
        // Array: 10,000 elements with numbers 1 to 100 repeated randomly
        int[] test11 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            test11[i] = (i % 100) + 1;  // Numbers 1-100 repeated
        }
        String result11 = findSecondHighestWithFrequency(test11);
        // Second highest should be 99, appears 100 times
        boolean pass11 = result11.equals("99-100");
        System.out.println("Test 11 - Large Array Wide Range (10K): " + (pass11 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 10,000 elements with numbers 1-100");
        System.out.println("  Expected: 99-100 | Got: " + result11 + "\n");
        passCount += pass11 ? 1 : 0;
        failCount += pass11 ? 0 : 1;

        // ============ COMPLEX PATTERNS ============

        // Test 12: Alternating high and low numbers
        // Array: alternates between 100 and 1
        // Unique sorted desc: [100, 1]
        // Second highest: 1, appears 50 times
        int[] test12 = new int[100];
        for (int i = 0; i < 100; i++) {
            test12[i] = (i % 2 == 0) ? 100 : 1;
        }
        String result12 = findSecondHighestWithFrequency(test12);
        boolean pass12 = result12.equals("1-50");
        System.out.println("Test 12 - Alternating Pattern: " + (pass12 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: Alternating 100 and 1");
        System.out.println("  Expected: 1-50 | Got: " + result12 + "\n");
        passCount += pass12 ? 1 : 0;
        failCount += pass12 ? 0 : 1;

        // Test 13: Reverse sorted array
        // Array: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        // Unique sorted desc: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        // Second highest: 9, appears 1 time
        int[] test13 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        String result13 = findSecondHighestWithFrequency(test13);
        boolean pass13 = result13.equals("9-1");
        System.out.println("Test 13 - Reverse Sorted: " + (pass13 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
        System.out.println("  Expected: 9-1 | Got: " + result13 + "\n");
        passCount += pass13 ? 1 : 0;
        failCount += pass13 ? 0 : 1;

        // ============ FINAL SUMMARY REPORT ============

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                     TEST SUMMARY REPORT                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\nTotal Tests Run:  " + (passCount + failCount));
        System.out.println("Tests Passed:     " + passCount);
        System.out.println("Tests Failed:     " + failCount);
        System.out.println("Success Rate:     " +
                String.format("%.1f%%", (passCount * 100.0 / (passCount + failCount))));

        System.out.println("\n" + "═".repeat(60));
        if (failCount == 0) {
            System.out.println("✓ ALL TESTS PASSED - SOLUTION IS CORRECT");
        } else {
            System.out.println("✗ SOME TESTS FAILED - CHECK OUTPUT ABOVE");
        }
        System.out.println("═".repeat(60));
    }
}