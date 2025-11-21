package com.interview.notes.code.year.y2025.feb.Goldman.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplementaryPairsTest {

    // Helper method to check if string can be rearranged to palindrome
    private static boolean canFormPalindrome(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) oddCount++;
        }
        return oddCount <= 1;
    }

    public static long countComplementaryPairs(List<String> stringData) {
        if (stringData == null || stringData.isEmpty()) {
            return 0;
        }

        int n = stringData.size();
        long pairCount = 0;

        // Check each possible pair
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check both concatenation orders
                String concat1 = stringData.get(i) + stringData.get(j);
                String concat2 = stringData.get(j) + stringData.get(i);

                if (canFormPalindrome(concat1) || canFormPalindrome(concat2)) {
                    pairCount++;
                }
            }
        }

        return pairCount;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1
        List<String> test1 = Arrays.asList("ball", "all", "call", "bal");
        testCase(test1, 3, "Test Case 1");

        // Test Case 2
        List<String> test2 = Arrays.asList("eye", "aa", "c");
        testCase(test2, 2, "Test Case 2");

        // Edge Cases
        testCase(new ArrayList<>(), 0, "Empty List");
        testCase(List.of("a"), 0, "Single Element");

        // Test Case with identical strings
        testCase(Arrays.asList("aa", "aa", "aa"), 3, "Identical Strings");

        // Large Input Test (create strings that definitely form pairs)
        List<String> largeTest = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeTest.add("aa");
        }
        testCase(largeTest, (999L * 1000L) / 2L, "Large Input Test");
    }

    private static void testCase(List<String> input, long expected, String testName) {
        long result = countComplementaryPairs(input);
        System.out.println(testName + ": " +
                (result == expected ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}
