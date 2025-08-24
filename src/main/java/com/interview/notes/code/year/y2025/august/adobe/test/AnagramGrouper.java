package com.interview.notes.code.year.y2025.august.adobe.test;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramGrouper {

    // Main method for testing
    public static void main(String[] args) {
        AnagramGrouper solution = new AnagramGrouper();

        // Test Case 1: Normal case with multiple anagrams
        String[] test1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = solution.groupAnagrams(test1);
        System.out.println("Test 1: " + (validateResult(result1,
                Arrays.asList(
                        Arrays.asList("bat"),
                        Arrays.asList("nat", "tan"),
                        Arrays.asList("ate", "eat", "tea")
                )) ? "PASS" : "FAIL"));

        // Test Case 2: Empty string
        String[] test2 = {""};
        List<List<String>> result2 = solution.groupAnagrams(test2);
        System.out.println("Test 2: " + (validateResult(result2,
                Arrays.asList(Arrays.asList(""))) ? "PASS" : "FAIL"));

        // Test Case 3: Single character
        String[] test3 = {"a"};
        List<List<String>> result3 = solution.groupAnagrams(test3);
        System.out.println("Test 3: " + (validateResult(result3,
                Arrays.asList(Arrays.asList("a"))) ? "PASS" : "FAIL"));

        // Test Case 4: Large input test
        String[] test4 = generateLargeInput(1000);
        long startTime = System.currentTimeMillis();
        List<List<String>> result4 = solution.groupAnagrams(test4);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 4 (Large Input): " +
                (result4.size() > 0 ? "PASS" : "FAIL") +
                " (Time taken: " + (endTime - startTime) + "ms)");
    }

    // Helper method to validate test results
    private static boolean validateResult(List<List<String>> actual, List<List<String>> expected) {
        if (actual.size() != expected.size()) return false;

        // Sort both actual and expected lists for comparison
        actual = sortGroups(actual);
        expected = sortGroups(expected);

        return actual.toString().equals(expected.toString());
    }

    // Helper method to sort groups for comparison
    private static List<List<String>> sortGroups(List<List<String>> groups) {
        return groups.stream()
                .map(list -> {
                    List<String> sorted = new ArrayList<>(list);
                    Collections.sort(sorted);
                    return sorted;
                })
                .sorted((a, b) -> a.toString().compareTo(b.toString()))
                .collect(Collectors.toList());
    }

    // Helper method to generate large test input
    private static String[] generateLargeInput(int size) {
        String[] result = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            result[i] = sb.toString();
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // Handle edge case where input array is null
        if (strs == null) return new ArrayList<>();

        // Create a HashMap to store sorted string as key and list of original strings as value
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Process each string in the input array using Java 8 Streams
        Arrays.stream(strs).forEach(str -> {
            // Convert string to char array for sorting
            char[] chars = str.toCharArray();
            // Sort the characters to create a key
            Arrays.sort(chars);
            // Create sorted key string
            String key = new String(chars);

            // Get or create list for this key and add current string
            anagramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        });

        // Convert map values to List using streams
        return new ArrayList<>(anagramMap.values());
    }
}
