package com.interview.notes.code.year.y2024.oct24.amazon.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordSimilarityChecker {

    public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < newPasswords.size(); i++) {
            String newPass = newPasswords.get(i);
            String oldPass = oldPasswords.get(i);
            results.add(canTransformToSubsequence(newPass, oldPass) ? "YES" : "NO");
        }

        return results;
    }

    private static boolean canTransformToSubsequence(String newPass, String oldPass) {
        // Create all possible transformations by changing characters cyclically
        for (int mask = 0; mask < (1 << newPass.length()); mask++) {
            StringBuilder transformed = new StringBuilder(newPass);

            // Apply transformations based on binary mask
            for (int j = 0; j < newPass.length(); j++) {
                if ((mask & (1 << j)) != 0) {
                    char c = transformed.charAt(j);
                    transformed.setCharAt(j, c == 'z' ? 'a' : (char) (c + 1));
                }
            }

            // Check if oldPass is subsequence of transformed
            if (isSubsequence(transformed.toString(), oldPass)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSubsequence(String str, String sub) {
        int j = 0;
        for (int i = 0; i < str.length() && j < sub.length(); i++) {
            if (str.charAt(i) == sub.charAt(j)) {
                j++;
            }
        }
        return j == sub.length();
    }

    public static void main(String[] args) {
        // Test Case 1
        List<String> newPasswords1 = Arrays.asList("aaaa", "bzz");
        List<String> oldPasswords1 = Arrays.asList("bcd", "az");
        testCase(1, newPasswords1, oldPasswords1, Arrays.asList("NO", "YES"));

        // Test Case 2
        List<String> newPasswords2 = Arrays.asList("aaccbbee", "aab");
        List<String> oldPasswords2 = Arrays.asList("bdbf", "aee");
        testCase(2, newPasswords2, oldPasswords2, Arrays.asList("YES", "NO"));

        // Large Data Test Case
        testLargeDataCase();

        // Edge Cases
        testEdgeCases();
    }

    private static void testCase(int caseNum, List<String> newPasswords,
                                 List<String> oldPasswords, List<String> expected) {
        List<String> result = findSimilarities(newPasswords, oldPasswords);
        System.out.println("Test Case " + caseNum + ":");
        System.out.println("Input: ");
        System.out.println("newPasswords: " + newPasswords);
        System.out.println("oldPasswords: " + oldPasswords);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testLargeDataCase() {
        // Generate large test case
        List<String> newPasswords = new ArrayList<>();
        List<String> oldPasswords = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            newPasswords.add("abcdefghij");
            oldPasswords.add("aceg");
        }

        long startTime = System.currentTimeMillis();
        List<String> result = findSimilarities(newPasswords, oldPasswords);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Data Test Case:");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Status: " + (result.size() == 10 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testEdgeCases() {
        // Test empty strings
        List<String> newPasswords = List.of("");
        List<String> oldPasswords = List.of("");
        testCase(3, newPasswords, oldPasswords, List.of("YES"));

        // Test single character
        newPasswords = List.of("a");
        oldPasswords = List.of("b");
        testCase(4, newPasswords, oldPasswords, List.of("YES"));
    }
}