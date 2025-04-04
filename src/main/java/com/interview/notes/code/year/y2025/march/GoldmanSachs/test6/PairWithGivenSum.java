package com.interview.notes.code.year.y2025.march.GoldmanSachs.test6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PairWithGivenSum {

    // Method to check if there exists a pair with the given sum in the array
    public static boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (seen.contains(target - num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    // Helper method to run a single test case and print the result
    public static void runTest(int testCaseNumber, int[] arr, int target, boolean expected) {
        boolean result = hasPairWithSum(arr, target);
        if (result == expected) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL");
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Provided test case
        int[] test1 = {10, 20, 30, 40, 50, 60, 70, 80};
        runTest(1, test1, 100, true); // e.g., 20 + 80 or 40 + 60

        // Test Case 2: No pair exists
        int[] test2 = {1, 2, 3, 4, 5};
        runTest(2, test2, 100, false);

        // Test Case 3: Edge case: empty array
        int[] test3 = {};
        runTest(3, test3, 100, false);

        // Test Case 4: Edge case: single element array
        int[] test4 = {50};
        runTest(4, test4, 100, false);

        // Test Case 5: Pair with negative numbers
        int[] test5 = {-20, 120, 30, -10, 90};
        runTest(5, test5, 100, true); // -20 + 120 = 100

        // Test Case 6: Array with duplicates
        int[] test6 = {50, 50, 20, 80};
        runTest(6, test6, 100, true); // 50 + 50 = 100

        // Test Case 7: Large data input test
        int size = 1000000;
        int[] largeTest = new int[size];
        Random rand = new Random();
        boolean expectedLargeTest = false;
        // Generate random data and purposely include one valid pair
        for (int i = 0; i < size; i++) {
            largeTest[i] = rand.nextInt(1000);
        }
        // Inject a valid pair manually if not already present
        largeTest[0] = 47;
        largeTest[1] = 53; // 47 + 53 = 100
        expectedLargeTest = true;
        runTest(7, largeTest, 100, expectedLargeTest);
    }
}
