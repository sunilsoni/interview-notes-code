package com.interview.notes.code.months.oct24.test18;

public class VowelSubstringTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    // Corrected solution
    public static long vowelSubstring(String s) {
        if (s == null || s.length() < 5) return 0;

        int[] vowelCount = new int[128];
        boolean[] isVowel = new boolean[128];
        isVowel['a'] = isVowel['e'] = isVowel['i'] = isVowel['o'] = isVowel['u'] = true;

        long result = 0;
        int distinctVowels = 0;

        // Check each possible starting position
        for (int i = 0; i <= s.length() - 5; i++) {
            // Reset counters for each new starting position
            distinctVowels = 0;
            for (int v = 0; v < 128; v++) {
                vowelCount[v] = 0;
            }

            // Check substring starting at position i
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);

                // Break if we hit a consonant
                if (!isVowel[c]) {
                    break;
                }

                // Count new vowel
                if (vowelCount[c] == 0) {
                    distinctVowels++;
                }
                vowelCount[c]++;

                // If we found all 5 vowels, increment result
                if (distinctVowels == 5) {
                    result++;
                }
            }
        }

        return result;
    }

    private static void assertEquals(long expected, long actual, String testName) {
        totalTests++;
        if (expected == actual) {
            passedTests++;
            System.out.println("✓ PASS: " + testName);
        } else {
            System.out.println("✗ FAIL: " + testName + " (Expected: " + expected + ", Got: " + actual + ")");
        }
    }

    private static void testBasicCases() {
        System.out.println("\nBasic Test Cases:");
        assertEquals(2, vowelSubstring("aeiouxa"), "Test 1: aeiouxa");
        assertEquals(1, vowelSubstring("axyzaeiou"), "Test 2: axyzaeiou");
        assertEquals(4, vowelSubstring("aeiouaeiou"), "Test 3: aeiouaeiou");
    }

    private static void testEdgeCases() {
        System.out.println("\nEdge Cases:");
        assertEquals(0, vowelSubstring(""), "Empty string");
        assertEquals(0, vowelSubstring(null), "Null string");
        assertEquals(0, vowelSubstring("abc"), "Short string");
        assertEquals(0, vowelSubstring("aei"), "Incomplete vowels");
        assertEquals(0, vowelSubstring("aeiox"), "Missing vowel");
    }

    private static void testLargeInputs() {
        System.out.println("\nLarge Input Tests:");

        // Test 1: Repeating pattern
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            sb1.append("aeiou");
        }
        String largeInput1 = sb1.toString();

        long startTime = System.currentTimeMillis();
        long result1 = vowelSubstring(largeInput1);
        long duration = System.currentTimeMillis() - startTime;

        System.out.println("Large Input Test 1 (100,000 chars):");
        System.out.println("Duration: " + duration + "ms");
        System.out.println(duration <= 3000 ? "✓ PASS" : "✗ FAIL (Time Limit Exceeded)");

        // Test 2: Random vowels
        StringBuilder sb2 = new StringBuilder();
        String vowels = "aeiou";
        for (int i = 0; i < 100000; i++) {
            sb2.append(vowels.charAt(i % 5));
        }
        String largeInput2 = sb2.toString();

        startTime = System.currentTimeMillis();
        long result2 = vowelSubstring(largeInput2);
        duration = System.currentTimeMillis() - startTime;

        System.out.println("\nLarge Input Test 2 (100,000 chars):");
        System.out.println("Duration: " + duration + "ms");
        System.out.println(duration <= 3000 ? "✓ PASS" : "✗ FAIL (Time Limit Exceeded)");
    }

    public static void main(String[] args) {
        System.out.println("Starting Vowel Substring Tests");
        System.out.println("============================");

        long startTime = System.currentTimeMillis();

        testBasicCases();
        testEdgeCases();
        testLargeInputs();

        long totalDuration = System.currentTimeMillis() - startTime;

        System.out.println("\nTest Summary");
        System.out.println("============================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Total Time: " + totalDuration + "ms");

        if (passedTests == totalTests) {
            System.out.println("\nALL TESTS PASSED! ✓");
        } else {
            System.out.println("\nSOME TESTS FAILED! ✗");
            System.exit(1);
        }
    }
}