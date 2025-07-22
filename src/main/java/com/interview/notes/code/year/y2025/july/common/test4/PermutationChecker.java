package com.interview.notes.code.year.y2025.july.common.test4;

public class PermutationChecker {

    public static boolean isPermutationPossible(String source, String target) {
        if (source == null || target == null) {
            return false;
        }

        // Create count array for all possible characters (ASCII)
        int[] charCount = new int[128];

        // Count characters in source string
        source.chars().forEach(c -> charCount[c]++);

        // Check if target can be formed
        for (char c : target.toCharArray()) {
            if (charCount[c] == 0) {
                return false;
            }
            charCount[c]--;
        }

        return true;
    }

    public static void main(String[] args) {
        runTestCase("entertainment", "mat", true);
        runTestCase("entertainment", "maat", false);
        runTestCase("entertainment", "pat", false);
        runTestCase("entertainment", "tent", true);
        runTestCase("entertainment", "", true);
        runTestCase("", "mat", false);
        runTestCase(null, "mat", false);
        runTestCase("entertainment", null, false);

        // Large data test
        String largeSource = "entertainment".repeat(100000);
        runTestCase(largeSource, "entertainment", true);
    }

    private static void runTestCase(String source, String target, boolean expected) {
        long startTime = System.nanoTime();
        boolean result = isPermutationPossible(source, target);
        long endTime = System.nanoTime();

        System.out.printf("Test Case:%n" +
                        "Source: %s%n" +
                        "Target: %s%n" +
                        "Expected: %b%n" +
                        "Result: %b%n" +
                        "Status: %s%n" +
                        "Time: %.3f ms%n%n",
                source != null ? (source.length() > 20 ? source.substring(0, 17) + "..." : source) : "null",
                target,
                expected,
                result,
                expected == result ? "PASS" : "FAIL",
                (endTime - startTime) / 1_000_000.0);
    }
}
