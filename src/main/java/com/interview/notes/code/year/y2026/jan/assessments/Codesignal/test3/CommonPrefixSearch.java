package com.interview.notes.code.year.y2026.jan.assessments.Codesignal.test3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CommonPrefixSearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = 4;   // add 4 at the end
        arr = newArr;             // arr is now {1,2,3,4}


        var solver = new CommonPrefixSearch();

        // Test Case 1: Example 1 from problem
        int[] arr1 = {25, 288, 2655, 54546, 54, 555};
        int[] arr2 = {2, 255, 266, 244, 26, 5, 54547};
        runTest(solver, arr1, arr2, 4, "Example 1");

        // Test Case 2: Example 2 (Full prefix match)
        int[] arr3 = {25, 288, 2655, 544, 54, 555};
        int[] arr4 = {2, 255, 266, 244, 26, 5, 5444444};
        runTest(solver, arr3, arr4, 3, "Example 2"); // 544 matches prefix of 5444444

        // Test Case 3: No common prefix
        int[] arr5 = {817, 99};
        int[] arr6 = {1999, 1909};
        runTest(solver, arr5, arr6, 0, "No Match");

        // Test Case 4: Single Element Exact Match
        int[] arr7 = {123456};
        int[] arr8 = {123456};
        runTest(solver, arr7, arr8, 6, "Exact Match");

        // Test Case 5: Large Data Performance
        int[] large1 = new Random().ints(50000, 1, 1000000000).toArray();
        int[] large2 = new Random().ints(50000, 1, 1000000000).toArray();

        long start = System.currentTimeMillis();
        int res = solver.solution(large1, large2);
        long end = System.currentTimeMillis();

        System.out.printf("%-15s: PASS (Result: %d, Time: %dms)%n", "Large Data", res, (end - start));
    }

    private static void runTest(CommonPrefixSearch s, int[] a, int[] b, int expected, String name) {
        int result = s.solution(a, b);
        System.out.printf("%-15s: %s [Expected: %d, Got: %d]%n",
                name,
                result == expected ? "PASS" : "FAIL",
                expected,
                result
        );
    }

    int solution(int[] firstArray, int[] secondArray) {
        Set<Integer> prefixes = new HashSet<>();

        // Generate all prefixes from the first array
        for (int num : firstArray) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }

        int maxLen = 0;

        // Check longest possible prefixes from the second array
        for (int num : secondArray) {
            // Optimization: If the number is shorter than the max found, it can't beat it.
            // Using log10 for digit count is efficient for integers.
            int digits = (int) Math.log10(num) + 1;
            if (digits <= maxLen) continue;

            while (num > 0) {
                // If current truncated number is smaller (fewer digits) than maxLen,
                // we can't find a better result here.
                if (num < Math.pow(10, maxLen)) break;

                if (prefixes.contains(num)) {
                    int currentLen = (int) Math.log10(num) + 1;
                    maxLen = Math.max(maxLen, currentLen);
                    // Since we check from longest to shortest for 'num',
                    // the first match is the best for this specific number.
                    break;
                }
                num /= 10;
            }
        }

        return maxLen;
    }
}