package com.interview.notes.code.year.y2025.may.amazon.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmazonAcademyScholarship {

    public static long findMinimumDays(List<Integer> pages, int k, int p) {
        int n = pages.size();
        long days = 0;
        int[] remainingPages = pages.stream().mapToInt(i -> i).toArray();

        // Continue until all pages are read
        while (Arrays.stream(remainingPages).anyMatch(pg -> pg > 0)) {
            // Find the first non-zero chapter to start
            int start = 0;
            while (start < n && remainingPages[start] == 0) {
                start++;
            }

            // Calculate how many pages can be read from contiguous k chapters
            int end = Math.min(start + k, n);

            for (int i = start; i < end; i++) {
                remainingPages[i] = Math.max(0, remainingPages[i] - p);
            }

            days++;
        }

        return days;
    }

    // Main method for testing
    public static void main(String[] args) {
        test(Arrays.asList(3, 4), 1, 2, 4);  // Expected output: 4
        test(Arrays.asList(5, 1, 2), 3, 3, 2);  // Expected output: 2
        test(Arrays.asList(3, 1, 4), 2, 2, 4);  // Expected output: 4

        // Large test case
        List<Integer> largeTest = IntStream.generate(() -> 1000000000)
                .limit(100000)
                .boxed()
                .collect(Collectors.toList());
        test(largeTest, 100000, 1000000000, 1); // Expected output: 1
    }

    private static void test(List<Integer> pages, int k, int p, long expected) {
        long result = findMinimumDays(pages, k, p);
        if (result == expected) {
            System.out.println("PASS: " + result);
        } else {
            System.out.println("FAIL: Expected " + expected + " but got " + result);
        }
    }
}