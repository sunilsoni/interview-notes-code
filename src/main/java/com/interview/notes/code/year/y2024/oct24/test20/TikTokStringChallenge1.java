package com.interview.notes.code.year.y2024.oct24.test20;

public class TikTokStringChallenge1 {

    public static int getMinTransformations(String caption) {
        if (caption == null || caption.length() < 2) {
            return 0;
        }

        int transformations = 0;
        char[] chars = caption.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                if (chars[i] != chars[i + 1]) {
                    transformations++;
                    chars[i] = chars[i + 1];
                }
            } else if (i == chars.length - 1) {
                if (chars[i] != chars[i - 1]) {
                    transformations++;
                    chars[i] = chars[i - 1];
                }
            } else {
                if (chars[i] != chars[i - 1] && chars[i] != chars[i + 1]) {
                    transformations++;
                    if (chars[i - 1] == chars[i + 1]) {
                        chars[i] = chars[i - 1];
                    } else {
                        chars[i] = (char) Math.min(chars[i - 1], chars[i + 1]);
                    }
                }
            }
        }

        return transformations;
    }

    public static void main(String[] args) {
        // Test cases
        testCase("aca", 2);
        testCase("abab", 2);
        testCase("abcdef", 3);
        testCase("aaa", 0);
        testCase("ab", 1);
        testCase("zz", 0);
        testCase("zyxwvutsrqponmlkjihgfedcba", 13);

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char) ('a' + i % 26));
        }
        testCase(largeInput.toString(), 49999);

        System.out.println("All test cases completed.");
    }

    private static void testCase(String caption, int expected) {
        long startTime = System.nanoTime();
        int result = getMinTransformations(caption);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        boolean passed = result == expected;
        System.out.println("Test case: " + caption.substring(0, Math.min(caption.length(), 20)) +
                (caption.length() > 20 ? "..." : ""));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Execution time: " + duration + " ms");
        System.out.println();
    }
}
