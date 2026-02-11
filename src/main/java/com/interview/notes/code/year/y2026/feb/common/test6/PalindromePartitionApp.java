package com.interview.notes.code.year.y2026.feb.common.test6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromePartitionApp { // Main class

    // Method to check palindrome
    static boolean isPalindrome(String s) { // Takes string input
        int l = 0; // Left pointer start
        int r = s.length() - 1; // Right pointer end

        while (l < r) { // Loop until pointers meet
            if (s.charAt(l) != s.charAt(r)) // Compare characters
                return false; // If mismatch, not palindrome
            l++; // Move left forward
            r--; // Move right backward
        }
        return true; // If all matched, palindrome
    }

    // Method to partition words
    static Map<Boolean, List<String>> partition(List<String> words) { // Takes list
        return words.stream() // Convert list to stream
                .collect(Collectors.partitioningBy( // Partition based on condition
                        PalindromePartitionApp::isPalindrome // Use method reference
                ));
    }

    // Simple test helper
    static void test(List<String> input, Map<Boolean, List<String>> expected) { // Test method
        Map<Boolean, List<String>> result = partition(input); // Get actual result
        boolean pass = result.equals(expected); // Compare expected and actual
        System.out.println(pass ? "PASS" : "FAIL"); // Print result
        System.out.println(result); // Print output
    }

    public static void main(String[] args) { // Main method

        // Test case 1
        List<String> words1 = List.of("radar", "level", "java", "stream");
        Map<Boolean, List<String>> expected1 = Map.of(
                true, List.of("radar", "level"),
                false, List.of("java", "stream")
        );
        test(words1, expected1);

        // Test case 2
        List<String> words2 = List.of("madam", "ma", "dam", "abc", "aa");
        Map<Boolean, List<String>> expected2 = Map.of(
                true, List.of("madam", "ma", "aa"),
                false, List.of("dam", "abc")
        );
        test(words2, expected2);

        // Large data test
        List<String> big = IntStream.range(0, 10000)
                .mapToObj(i -> i % 2 == 0 ? "radar" : "java")
                .toList();
        Map<Boolean, List<String>> resultBig = partition(big);
        System.out.println(resultBig.get(true).size() == 5000 ? "PASS" : "FAIL");
    }
}
