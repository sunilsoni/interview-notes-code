package com.interview.notes.code.year.y2024.oct24.test18;

public class VowelSubstringTest1 {
    private static int totalTests = 0;
    private static int passedTests = 0;

    // Main solution implementation
    public static long vowelSubstring(String s) {
        if (s == null || s.length() < 5) return 0;

        long count = 0;
        boolean[] isVowel = new boolean[128];
        isVowel['a'] = isVowel['e'] = isVowel['i'] = isVowel['o'] = isVowel['u'] = true;

        for (int i = 0; i <= s.length() - 5; i++) {
            boolean[] found = new boolean[128];
            int vowelCount = 0;
            int j;

            for (j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!isVowel[c]) break;
                if (!found[c]) {
                    found[c] = true;
                    vowelCount++;
                }
                if (vowelCount == 5) {
                    count++;
                }
            }
        }
        return count;
    }

    // Custom test framework methods
    private static void assertEquals(long expected, long actual, String testName) {
        totalTests++;
        if (expected == actual) {
            passedTests++;
            System.out.println("✓ PASS: " + testName);
        } else {
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Expected: " + expected + ", but got: " + actual);
        }
    }

    private static void assertPerformance(Runnable test, long maxTimeMs, String testName) {
        totalTests++;
        long startTime = System.currentTimeMillis();
        test.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (duration <= maxTimeMs) {
            passedTests++;
            System.out.println("✓ PASS: " + testName + " (Completed in " + duration + "ms)");
        } else {
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Expected completion within " + maxTimeMs + "ms, but took " + duration + "ms");
        }
    }

    // Test cases
    private static void testBasicCases() {
        System.out.println("\nRunning Basic Test Cases:");
        assertEquals(2, vowelSubstring("aeiouxa"), "Test Case 0: aeiouxa");
        assertEquals(1, vowelSubstring("axyzaeiou"), "Test Case 1: axyzaeiou");
    }

    private static void testEdgeCases() {
        System.out.println("\nRunning Edge Cases:");
        assertEquals(0, vowelSubstring(""), "Empty string");
        assertEquals(0, vowelSubstring(null), "Null string");
        assertEquals(0, vowelSubstring("abc"), "Short string");
        assertEquals(0, vowelSubstring("aei"), "Incomplete vowels");
    }

    private static void testComplexCases() {
        System.out.println("\nRunning Complex Cases:");
        assertEquals(4, vowelSubstring("aeiouaeiou"), "Double vowel sequence");
        assertEquals(0, vowelSubstring("bcdpqr"), "No vowels");
        assertEquals(1, vowelSubstring("aeioubc"), "Single valid sequence");
    }

    private static void testLargeInput() {
        System.out.println("\nRunning Performance Tests:");

        // Test with large input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            sb.append("aeiou");
        }
        final String largeInput = sb.toString();

        assertPerformance(() -> {
            long result = vowelSubstring(largeInput);
            if (result <= 0) System.out.println("Warning: Unexpected result for large input");
        }, 1000, "Large input test (100,000 chars)");
    }

    public static void main(String[] args) {
        System.out.println("Starting Vowel Substring Tests\n" +
                "================================");

        long startTime = System.currentTimeMillis();

        // Run all test cases
        testBasicCases();
        testEdgeCases();
        testComplexCases();
        testLargeInput();

        long endTime = System.currentTimeMillis();

        // Print summary
        System.out.println("\nTest Summary");
        System.out.println("================================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Total Time: " + (endTime - startTime) + "ms");

        if (passedTests == totalTests) {
            System.out.println("\nALL TESTS PASSED! ✓");
        } else {
            System.out.println("\nSOME TESTS FAILED! ✗");
            System.exit(1);
        }
    }
}