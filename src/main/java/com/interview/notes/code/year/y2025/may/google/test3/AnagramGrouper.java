package com.interview.notes.code.year.y2025.may.google.test3;

import java.util.*;

public class AnagramGrouper {
    
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Edge case: if input array is null or empty
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // Using HashMap to store anagram groups
        // Key: Sorted string (e.g., "aet" for "eat", "tea", "ate")
        // Value: List of original strings that are anagrams
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Process each string in the input array
        Arrays.stream(strs).forEach(str -> {
            // Convert string to char array for sorting
            char[] chars = str.toCharArray();
            // Sort characters to create a key
            Arrays.sort(chars);
            String key = new String(chars);
            
            // Get or create list for this anagram group
            anagramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        });

        // Convert map values to list and return
        return new ArrayList<>(anagramMap.values());
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case
        test(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
             "[[eat, tea, ate], [tan, nat], [bat]]");

        // Test Case 2: Empty array
        test(new String[]{}, "[]");

        // Test Case 3: Single element
        test(new String[]{"a"}, "[[a]]");

        // Test Case 4: All same anagrams
        test(new String[]{"abc", "bca", "cab"}, "[[abc, bca, cab]]");

        // Test Case 5: No anagrams
        test(new String[]{"dog", "cat", "pig"}, "[[dog], [cat], [pig]]");

        // Test Case 6: Large input (performance test)
        testLargeInput();
    }

    private static void test(String[] input, String expectedOutput) {
        List<List<String>> result = groupAnagrams(input);
        String resultStr = result.toString();
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + resultStr);
        System.out.println("Test " + (resultStr.equals(expectedOutput) ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private static void testLargeInput() {
        // Generate large input
        String[] largeInput = new String[10000];
        for (int i = 0; i < 10000; i++) {
            largeInput[i] = "word" + i;
        }
        
        long startTime = System.currentTimeMillis();
        List<List<String>> result = groupAnagrams(largeInput);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large Input Test (10000 elements)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Number of groups: " + result.size());
        System.out.println();
    }
}
