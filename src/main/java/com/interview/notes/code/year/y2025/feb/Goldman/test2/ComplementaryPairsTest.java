package com.interview.notes.code.year.y2025.feb.Goldman.test2;

import java.util.*;

public class ComplementaryPairsTest {
    // Optimized method to check if string can form palindrome using bit manipulation
    private static boolean canFormPalindrome(String s) {
        int mask = 0;
        for (char c : s.toCharArray()) {
            mask ^= (1 << (c - 'a'));
        }
        return (mask & (mask - 1)) == 0; // Check if at most one bit is set
    }

    public static long countComplementaryPairs(List<String> stringData) {
        if (stringData == null || stringData.size() < 2) return 0;

        int n = stringData.size();
        long pairCount = 0;

        // Create frequency map for string signatures
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (String s : stringData) {
            int mask = 0;
            // Calculate character frequency mask
            for (char c : s.toCharArray()) {
                mask ^= (1 << (c - 'a'));
            }
            freqMap.put(mask, freqMap.getOrDefault(mask, 0) + 1);
        }

        // Count pairs with same signature
        for (int count : freqMap.values()) {
            if (count > 1) {
                pairCount += (long) count * (count - 1) / 2;
            }
        }

        return pairCount;
    }

    public static void main(String[] args) {
        // Test cases
        test(Arrays.asList("ball", "all", "call", "bal"), 3, "Test Case 1");
        test(Arrays.asList("eye", "aa", "c"), 2, "Test Case 2");

        // Edge cases
        test(new ArrayList<>(), 0, "Empty List");
        test(List.of("a"), 0, "Single Element");

        // Large input test
        List<String> largeTest = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest.add("aa" + (i % 100)); // Create strings that form pairs
        }
        test(largeTest, 49950, "Large Input Test");
    }

    private static void test(List<String> input, long expected, String testName) {
        long startTime = System.currentTimeMillis();
        long result = countComplementaryPairs(input);
        long endTime = System.currentTimeMillis();

        System.out.printf("%s: %s (Expected: %d, Got: %d, Time: %dms)%n",
                testName,
                result == expected ? "PASS" : "FAIL",
                expected,
                result,
                endTime - startTime);
    }
}
