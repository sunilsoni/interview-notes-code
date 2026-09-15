package com.interview.notes.code.year.y2026.september.common.test3;

import java.util.ArrayList;
import java.util.List;

public class Main { // Main class used to run our program.

    static int largest(List<Integer> nums) { // Method receives numbers and returns the largest number.
        if (nums.isEmpty()) // Checks if the collection has no values.
            throw new IllegalArgumentException("Empty list"); // Stops because an empty list has no largest number.

        int max = nums.getFirst(); // Java 21 gets the first value and uses it as the initial maximum.

        for (int n : nums) // Goes through every number in the collection.
            if (n > max) // Checks whether the current number is larger than our current maximum.
                max = n; // Updates maximum when a larger number is found.

        return max; // Returns the largest number after checking all values.
    }

    static void test(List<Integer> input, int expected) { // Reusable method for checking each test case.
        int actual = largest(input); // Calls our largest-number method.
        System.out.println( // Prints whether this test passed or failed.
            (actual == expected ? "PASS" : "FAIL") + // Compares actual and expected answers.
            " | Expected: " + expected + // Prints the expected value.
            " | Actual: " + actual // Prints the actual value returned by our method.
        );
    }

    static void main(String[] args) { // Program execution starts here.
        test(List.of(10, 45, 3, 99, 20), 99); // Tests normal positive numbers.
        test(List.of(5), 5); // Tests a collection containing only one number.
        test(List.of(-10, -5, -20), -5); // Tests negative numbers.
        test(List.of(7, 7, 7), 7); // Tests duplicate values.
        test(List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE), Integer.MAX_VALUE); // Tests integer limits.

        var large = new ArrayList<Integer>(); // Creates a collection for a large-data test.

        for (int i = 0; i < 1_000_000; i++) // Adds one million values to test large input.
            large.add(i); // Adds the current number into the collection.

        test(large, 999_999); // Verifies that the method handles one million values correctly.
    }
}