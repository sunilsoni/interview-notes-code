package com.interview.notes.code.year.y2025.march.tiktok.test1;

import java.util.stream.LongStream;

public class DecompressVideo {

    // This method computes the k-th lexicographically smallest string
    // that contains exactly x '0's and y '1's.
    public static String decompressVideo(int x, int y, long k) {
        // StringBuilder to efficiently build the result
        StringBuilder sb = new StringBuilder(x + y);

        // Loop through all positions from left to right
        for (int i = 0; i < x + y; i++) {
            if (x == 0) {
                // If no zeros left, fill the rest with '1'
                sb.append('1');
                y--;
            } else if (y == 0) {
                // If no ones left, fill the rest with '0'
                sb.append('0');
                x--;
            } else {
                // Calculate how many combinations start with '0'
                long waysWithZeroFirst = choose(x + y - 1, x - 1, k);

                if (waysWithZeroFirst >= k) {
                    // If the number of ways is >= k, we place '0'
                    sb.append('0');
                    x--;
                } else {
                    // Otherwise, place '1' and reduce k
                    sb.append('1');
                    y--;
                    k -= waysWithZeroFirst;
                }
            }
        }
        return sb.toString();
    }

    // choose(n, r, limit) returns C(n, r) but capped at 'limit' to avoid overflow.
    // This helps us decide if C(n, r) >= k without storing huge numbers.
    private static long choose(int n, int r, long limit) {
        // If r > n, return 0 (no ways)
        if (r > n) return 0;
        // If r == 0 or r == n, return 1
        if (r == 0 || r == n) return 1;

        // To minimize multiplications, use nCr symmetry: C(n, r) = C(n, n-r)
        r = Math.min(r, n - r);

        long result = 1;
        for (int i = 0; i < r; i++) {
            // Multiply by (n-i)
            result = result * (n - i) / (i + 1);

            // If result exceeds limit, cap it
            if (result >= limit) {
                return result;
            }
        }
        return result;
    }

    // A simple main method to run basic tests.
    public static void main(String[] args) {
        // Example tests:
        testCase(2, 2, 3, "0110");     // from the sample: 3rd combination among x=2,y=2 => "0110"
        testCase(3, 4, 35, "1111000"); // from the sample: the 35th combination => "1111000"
        
        // Additional tests:
        testCase(3, 2, 1, "00011");    // smallest for x=3, y=2
        testCase(3, 2, 10, "11000");   // largest for x=3, y=2 => total 10 combos
        testCase(1, 1, 1, "01");       // simplest case
        testCase(1, 1, 2, "10");       // next simplest case
        
        // Edge cases:
        testCase(5, 0, 1, "00000");    // only zeros
        testCase(0, 5, 1, "11111");    // only ones
    }

    // Helper method to compare expected vs. actual output and print pass/fail
    private static void testCase(int x, int y, long k, String expected) {
        String actual = decompressVideo(x, y, k);
        String result = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.println("x=" + x + ", y=" + y + ", k=" + k 
            + " => " + actual + " | Expected: " + expected + " => " + result);
    }
}