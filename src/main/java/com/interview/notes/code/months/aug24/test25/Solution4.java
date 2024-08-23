package com.interview.notes.code.months.aug24.test25;

import java.util.HashMap;
import java.util.Map;

class Solution4 {
    public static void main(String[] args) {
        Solution4 sol = new Solution4();

        // Test case 1
        int[] a1 = {2, -2, 5, 3};
        int[] b1 = {1, 5, -1, 1};
        long expected1 = 6;
        long result1 = sol.solution(a1, b1);
        System.out.println("Test case 1: " + (result1 == expected1 ? "PASSED" : "FAILED"));
        System.out.println("Expected: " + expected1 + ", Got: " + result1);

        // Test case 2
        int[] a2 = {25, 0};
        int[] b2 = {0, 25};
        long expected2 = 3;
        long result2 = sol.solution(a2, b2);
        System.out.println("Test case 2: " + (result2 == expected2 ? "PASSED" : "FAILED"));
        System.out.println("Expected: " + expected2 + ", Got: " + result2);

        // Test case 3: All elements are the same
        int[] a3 = {1, 1, 1, 1};
        int[] b3 = {1, 1, 1, 1};
        long expected3 = 6;
        long result3 = sol.solution(a3, b3);
        System.out.println("Test case 3: " + (result3 == expected3 ? "PASSED" : "FAILED"));
        System.out.println("Expected: " + expected3 + ", Got: " + result3);

        // Test case 4: Large numbers
        int[] a4 = {1000000000, -1000000000};
        int[] b4 = {-1000000000, 1000000000};
        long expected4 = 1;
        long result4 = sol.solution(a4, b4);
        System.out.println("Test case 4: " + (result4 == expected4 ? "PASSED" : "FAILED"));
        System.out.println("Expected: " + expected4 + ", Got: " + result4);

        // Test case 5: Single element arrays
        int[] a5 = {5};
        int[] b5 = {5};
        long expected5 = 0;
        long result5 = sol.solution(a5, b5);
        System.out.println("Test case 5: " + (result5 == expected5 ? "PASSED" : "FAILED"));
        System.out.println("Expected: " + expected5 + ", Got: " + result5);
    }

    long solution(int[] a, int[] b) {
        int n = a.length;
        Map<Long, Integer> diffCount = new HashMap<>();
        long result = 0;

        // First pass: count the differences
        for (int i = 0; i < n; i++) {
            long diff = (long) a[i] - b[i];
            diffCount.put(diff, diffCount.getOrDefault(diff, 0) + 1);
        }

        // Second pass: count the pairs
        for (int i = 0; i < n; i++) {
            long diff = (long) a[i] - b[i];
            result += diffCount.getOrDefault(diff, 0) - 1; // Subtract 1 to exclude the pair (i, i)
        }

        return result;
    }
}
