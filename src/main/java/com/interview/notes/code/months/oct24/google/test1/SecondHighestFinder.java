package com.interview.notes.code.months.oct24.google.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondHighestFinder {

    public static int findSecondHighest(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two numbers");
        }

        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num != highest) {
                secondHighest = num;
            }
        }

        if (secondHighest == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second highest number found");
        }

        return secondHighest;
    }

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        // Test case 1: Normal case
        test(Arrays.asList(1, 2, 3, 4, 5), 4);

        // Test case 2: Duplicate highest
        test(Arrays.asList(5, 5, 4, 3, 2), 4);

        // Test case 3: All same numbers
        try {
            test(Arrays.asList(1, 1, 1, 1), 0);
            System.out.println("FAIL: Test case 3 should throw an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Test case 3");
        }

        // Test case 4: Large input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeInput.add(i);
        }
        long startTime = System.currentTimeMillis();
        test(largeInput, 999998);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input execution time: " + (endTime - startTime) + "ms");

        // Test case 5: Negative numbers
        test(Arrays.asList(-5, -2, -8, -1), -2);

        // Test case 6: Empty list
        try {
            test(new ArrayList<>(), 0);
            System.out.println("FAIL: Test case 6 should throw an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Test case 6");
        }
    }

    private static void test(List<Integer> input, int expected) {
        try {
            int result = findSecondHighest(input);
            if (result == expected) {
                System.out.println("PASS: Input " + input + ", Expected " + expected + ", Got " + result);
            } else {
                System.out.println("FAIL: Input " + input + ", Expected " + expected + ", Got " + result);
            }
        } catch (Exception e) {
            System.out.println("FAIL: Input " + input + ", Unexpected exception: " + e.getMessage());
        }
    }
}
