package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    /**
     * Logic: Sorts the list in reverse order and sums the first two numbers.
     * Uses Java 8 Streams for brevity and Java 21 'var' for cleaner syntax.
     */
    public static int findMaxSum(List<Integer> list) {
        // Validation: If list is null or has fewer than 2 items, we return 0
        if (list == null || list.size() < 2) return 0;

        // Core Logic: Stream -> Sort Descending -> Take Top 2 -> Sum
        return list.stream()                              // Convert List to Stream source
                   .sorted(Comparator.reverseOrder())     // Sort elements: High to Low (O(N log N))
                   .limit(2)                              // Optimization: We only need the top 2 items
                   .mapToInt(Integer::intValue)           // Unbox Integer object to primitive int
                   .sum();                                // Terminal operation: Calculate total sum
    }

    // Main method to run tests directly (No JUnit required)
    public static void main(String[] args) {
        System.out.println("Running Test Suite...\n");    // UX: Log start of testing

        // Case 1: The example provided in the problem statement
        test("Base Case", List.of(5, 9, 7, 11), 20);      // Expect 9 + 11 = 20

        // Case 2: Handling duplicates (should pick the two 50s)
        test("Duplicates", List.of(50, 10, 50), 100);     // Expect 50 + 50 = 100

        // Case 3: Minimum valid input size
        test("Two Items", List.of(1, 99), 100);           // Expect 1 + 99 = 100

        // Case 4: Large Data Input (Performance Check)
        // Using 'var' (Java 10+) to reduce verbosity for complex types
        var largeData = new ArrayList<Integer>();         // Create a mutable list
        for (int i = 0; i < 1_000_000; i++) largeData.add(i); // Fill with 0 to 999,999
        // The two largest are 999,999 and 999,998. Sum = 1,999,997
        test("Large Data", largeData, 1999997);           // Verify logic holds for 1M items
    }

    // Simple test helper to check PASS/FAIL status
    static void test(String name, List<Integer> input, int expected) {
        long start = System.currentTimeMillis();          // Start timer for performance tracking
        int actual = findMaxSum(input);                   // Run the solution
        long time = System.currentTimeMillis() - start;   // Calculate execution time

        // specific check: Did we get what we wanted?
        String status = (actual == expected) ? "PASS" : "FAIL";

        // Print concise report for this test case
        System.out.printf("[%s] %-12s | Exp: %d, Got: %d | Time: %dms%n",
                status, name, expected, actual, time);
    }
}