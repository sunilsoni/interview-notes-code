package com.interview.notes.code.months.nov24.amazon.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordSimilarityChecker {

    public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < newPasswords.size(); i++) {
            results.add(isPasswordSimilar(newPasswords.get(i), oldPasswords.get(i)) ? "YES" : "NO");
        }
        return results;
    }

    private static boolean isPasswordSimilar(String newPassword, String oldPassword) {
        int newIndex = 0, oldIndex = 0;
        while (newIndex < newPassword.length() && oldIndex < oldPassword.length()) {
            if (oldPassword.charAt(oldIndex) == newPassword.charAt(newIndex) ||
                    isNextCyclicChar(newPassword.charAt(newIndex), oldPassword.charAt(oldIndex))) {
                oldIndex++;
            }
            newIndex++;
        }
        return oldIndex == oldPassword.length();
    }

    private static boolean isNextCyclicChar(char newChar, char oldChar) {
        return (newChar - 'a' + 1) % 26 + 'a' == oldChar;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(Arrays.asList("aaaa", "bzz"), Arrays.asList("bcd", "az"), Arrays.asList("NO", "YES"), "Sample Case 1");
        runTestCase(Arrays.asList("aaccbbee", "aab"), Arrays.asList("bdbf", "aee"), Arrays.asList("YES", "NO"), "Sample Case 0");
        runTestCase(Arrays.asList("baacbab", "accdb", "baacba"), Arrays.asList("abdbc", "ach", "abb"), Arrays.asList("YES", "NO", "YES"), "Example Case");

        // Edge cases
        runTestCase(Arrays.asList("a", "z"), Arrays.asList("a", "a"), Arrays.asList("YES", "YES"), "Edge Case: Single Character");
        runTestCase(Arrays.asList("abcdefghijklmnopqrstuvwxyz"), Arrays.asList("zyxwvutsrqponmlkjihgfedcba"), Arrays.asList("YES"), "Edge Case: Full Alphabet");

        // Large data case
        List<String> largeNewPasswords = new ArrayList<>();
        List<String> largeOldPasswords = new ArrayList<>();
        List<String> largeExpectedResults = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeNewPasswords.add("abcdefghijklmnopqrstuvwxyz".repeat(100));
            largeOldPasswords.add("zyxwvutsrqponmlkjihgfedcba".repeat(100));
            largeExpectedResults.add("YES");
        }
        runTestCase(largeNewPasswords, largeOldPasswords, largeExpectedResults, "Large Data Case");
    }

    private static void runTestCase(List<String> newPasswords, List<String> oldPasswords, List<String> expectedResults, String testName) {
        System.out.println("Running " + testName + "...");
        long startTime = System.currentTimeMillis();
        List<String> results = findSimilarities(newPasswords, oldPasswords);
        long endTime = System.currentTimeMillis();
        boolean passed = results.equals(expectedResults);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        if (!passed) {
            System.out.println("Expected: " + expectedResults);
            System.out.println("Actual: " + results);
        }
        System.out.println();
    }
}
