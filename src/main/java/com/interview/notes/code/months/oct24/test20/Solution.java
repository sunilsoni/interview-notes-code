package com.interview.notes.code.months.oct24.test20;

public class Solution {
    public static int getMinTransformations(String caption) {
        if (caption == null || caption.length() < 2) {
            return 0;
        }

        char[] chars = caption.toCharArray();
        int n = chars.length;
        int changes = 0;

        // Process characters in pairs
        for (int i = 0; i < n - 1; i++) {
            // If current character doesn't match with next character
            if (chars[i] != chars[i + 1]) {
                // If we can look ahead one more position
                if (i + 2 < n) {
                    // Choose the optimal transformation based on the next character
                    if (chars[i + 2] == chars[i]) {
                        chars[i + 1] = chars[i];
                        changes++;
                    } else if (chars[i + 2] == chars[i + 1]) {
                        chars[i] = chars[i + 1];
                        changes++;
                    } else {
                        // Make current and next character same with minimum change
                        changes += getMinChange(chars, i);
                    }
                } else {
                    // For last pair, make them same with minimum change
                    changes += getMinChange(chars, i);
                }
            }
        }

        return changes;
    }

    private static int getMinChange(char[] chars, int i) {
        // Calculate cost to change either current or next character
        int cost1 = Math.abs(chars[i + 1] - chars[i]);

        // Choose the transformation that requires minimum change
        if (chars[i] == 'a' || chars[i] == 'z') {
            chars[i] = chars[i + 1];
            return 1;
        } else if (chars[i + 1] == 'a' || chars[i + 1] == 'z') {
            chars[i + 1] = chars[i];
            return 1;
        } else {
            chars[i + 1] = chars[i];
            return 1;
        }
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "aca",      // Expected: 2
                "abab",     // Expected: 2
                "abcdef",   // Expected: 3
                "aaaaaa",   // Expected: 0
                "xyz",      // Expected: 1
                "abcdefghijklmnopqrstuvwxyz"  // Large input
        };

        for (String test : testCases) {
            long startTime = System.nanoTime();
            int result = getMinTransformations(test);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

            System.out.println("Input: " + test);
            System.out.println("Result: " + result);
            System.out.println("Time taken: " + duration + " ms");
            System.out.println();
        }

        // Test for large input
        StringBuilder largeCaseBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeCaseBuilder.append((char) ('a' + i % 26));
        }
        String largeCase = largeCaseBuilder.toString();

        long startTime = System.nanoTime();
        int result = getMinTransformations(largeCase);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;

        System.out.println("Large input length: " + largeCase.length());
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + duration + " ms");
    }
}