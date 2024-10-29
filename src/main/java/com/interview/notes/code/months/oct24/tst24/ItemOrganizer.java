package com.interview.notes.code.months.oct24.tst24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemOrganizer {

    public static int getMinimumOperations(List<Integer> items) {
        if (items == null || items.size() <= 1) {
            return 0;
        }

        int n = items.size();
        int totalOps = 0;

        // Convert to array for easier manipulation
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = items.get(i);
        }

        // For each position, try to minimize operations needed
        for (int i = 0; i < n - 1; i++) {
            if ((arr[i] % 2) == (arr[i + 1] % 2)) {
                // If current and next number have same parity
                int ops1 = getMinOpsToChangeParity(arr[i]);
                int ops2 = getMinOpsToChangeParity(arr[i + 1]);

                // Choose the minimum between changing current or next number
                if (ops1 <= ops2) {
                    arr[i] = arr[i] >> ops1;  // Divide by 2^ops1
                    totalOps += ops1;
                } else {
                    arr[i + 1] = arr[i + 1] >> ops2;  // Divide by 2^ops2
                    totalOps += ops2;
                }
            }
        }

        return totalOps;
    }

    private static int getMinOpsToChangeParity(int num) {
        if (num == 0) return 1;  // Special case for 0

        int ops = 0;
        while ((num & 1) == (num >> 1 & 1) && num > 0) {
            num >>= 1;
            ops++;
        }
        return ops + 1;
    }

    // Test method
    public static void runTest(List<Integer> input, int expected) {
        int result = getMinimumOperations(input);
        boolean passed = result == expected;
        System.out.println("Test case: " + input);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        // Test case 1: Example from problem statement
        runTest(Arrays.asList(6, 5, 4, 7, 3), 3);

        // Test case 2: Already organized array
        runTest(Arrays.asList(2, 1, 4, 7, 2), 0);

        // Test case 3: Another example from problem
        runTest(Arrays.asList(4, 10, 10, 6, 2), 2);

        // Test case 4: Single element array
        runTest(Arrays.asList(5), 0);

        // Test case 5: Two elements needing organization
        runTest(Arrays.asList(3, 3), 1);

        // Test case 6: Large numbers
        runTest(Arrays.asList(1073741824, 1073741824, 1073741824), 2);

        // Test case 7: Empty array
        runTest(new ArrayList<>(), 0);

        // Test case 8: Array with all same numbers
        runTest(Arrays.asList(2, 2, 2, 2, 2), 2);

        // Performance test with large input
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(rand.nextInt(1000000) + 1);
        }
        long startTime = System.currentTimeMillis();
        getMinimumOperations(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input (100000 elements) execution time: " +
                (endTime - startTime) + "ms");
    }
}