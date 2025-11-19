package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SecondHighestFinder {

    // Method returns second highest number and its count in "num-count" format
    public static String getSecondHighest(int[] arr) {

        // Convert int[] to a Stream<Integer> so we can use Stream operations
        List<Integer> list = IntStream.of(arr).boxed().toList();

        // Extract distinct numbers, sort descending, and pick the second element
        List<Integer> sorted = list.stream().distinct()
                                   .sorted(Comparator.reverseOrder())
                                   .toList();

        // If we do not have at least 2 unique values, return empty result
        if (sorted.size() < 2) return "";

        // Second highest value (index 1 after sorting in reverse)
        int second = sorted.get(1);

        // Count how many times this second highest number appears in original array
        long count = list.stream().filter(x -> x == second).count();

        // Build final result "second-highest-count"
        return second + "-" + count;
    }

    // Manual test harness (no JUnit)
    public static void main(String[] args) {

        // Test case 1
        test(new int[]{8,9,8,5,4,8,3,8}, "8-4");

        // Test case 2: simple
        test(new int[]{5,4,3,2}, "4-1");

        // Test case 3: duplicates
        test(new int[]{1,1,1,2,2}, "1-3");

        // Test case 4: large data test
        int[] large = IntStream.range(0, 1_000_00)
                               .map(i -> i % 50)
                               .toArray();
        test(large, getSecondHighest(large)); // Just checks no crash

        // Test case 5: not enough unique numbers
        test(new int[]{10,10,10}, "");

        // Test case 6: negative numbers
        test(new int[]{-1,-5,-1,-7,-5}, "-5-2");
    }

    // Helper method prints PASS/FAIL
    private static void test(int[] arr, String expected) {
        String result = getSecondHighest(arr);
        System.out.println((result.equals(expected) ? "PASS" : "FAIL")
                + " | Expected: " + expected + " | Got: " + result);
    }
}
