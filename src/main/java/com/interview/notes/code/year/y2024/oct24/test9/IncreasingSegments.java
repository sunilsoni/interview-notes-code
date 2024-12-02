package com.interview.notes.code.year.y2024.oct24.test9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncreasingSegments {

    public static int countIncreasingSegments(List<Integer> yCoordinates, int k) {
        int count = 0;
        int n = yCoordinates.size();

        // Check all possible segments of length k
        for (int i = 0; i <= n - k; i++) {
            boolean isIncreasing = true;
            // Check if the current segment is strictly increasing
            for (int j = i; j < i + k - 1; j++) {
                if (yCoordinates.get(j) >= yCoordinates.get(j + 1)) {
                    isIncreasing = false;
                    break;
                }
            }
            if (isIncreasing) {
                count++;
            }
        }
        return count;
    }

    // Main method to run all test cases
    public static void main(String[] args) {
        runTests();
    }

    // Test cases
    public static void runTests() {
        List<Integer> testCase1 = Arrays.asList(1, 2, 3, 4);
        assert countIncreasingSegments(testCase1, 4) == 1 : "Test case 1 failed";

        List<Integer> testCase2 = Arrays.asList(1, 2, 3, 3, 4, 5);
        assert countIncreasingSegments(testCase2, 3) == 2 : "Test case 2 failed";

        List<Integer> testCase3 = Arrays.asList(6, 5, 7, 8, 10);
        assert countIncreasingSegments(testCase3, 3) == 2 : "Test case 3 failed";

        // Edge case with k = 1 (every point is a segment)
        List<Integer> testCase4 = Arrays.asList(5, 6, 7);
        assert countIncreasingSegments(testCase4, 1) == 3 : "Test case 4 failed";

        // Edge case with all elements equal
        List<Integer> testCase5 = Arrays.asList(5, 5, 5, 5);
        assert countIncreasingSegments(testCase5, 2) == 0 : "Test case 5 failed";

        // Large test case
        List<Integer> largeTestCase = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeTestCase.add(i);
        }
        assert countIncreasingSegments(largeTestCase, 100000) == 1 : "Large test case failed";

        System.out.println("All test cases passed!");
    }
}
