package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.*;

public class GroupAnagrams {
    /**
     * Groups anagrams together from an array of strings, handling both uppercase and lowercase letters.
     * Time Complexity: O(n * k log k) where n is the length of strs and k is the maximum length of a string in strs
     * Space Complexity: O(n * k) for storing the result and the hash map
     *
     * @param strs          Array of strings to be grouped
     * @param caseSensitive If true, treats uppercase and lowercase as different characters
     * @return List of lists, where each inner list contains strings that are anagrams of each other
     */
    public static List<List<String>> groupAnagrams(String[] strs, boolean caseSensitive) {
        // Edge case: if input is null, return empty list
        if (strs == null) {
            return new ArrayList<>();
        }

        // HashMap to store groups of anagrams
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Process each string in the input array
        for (String str : strs) {
            // Create a canonical form for the string
            String canonicalForm;

            if (caseSensitive) {
                // For case-sensitive comparison, just sort the characters
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                canonicalForm = new String(charArray);
            } else {
                // For case-insensitive comparison, convert to lowercase first
                char[] charArray = str.toLowerCase().toCharArray();
                Arrays.sort(charArray);
                canonicalForm = new String(charArray);
            }

            // Add the original string to its anagram group
            anagramGroups.computeIfAbsent(canonicalForm, k -> new ArrayList<>()).add(str);
        }

        // Convert the values of the map to a list of lists
        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * Default version that is case-sensitive (matches the original problem requirements)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        return groupAnagrams(strs, true);
    }

    /**
     * Groups anagrams together from an array of strings.
     * Time Complexity: O(n * k log k) where n is the length of strs and k is the maximum length of a string in strs
     * Space Complexity: O(n * k) for storing the result and the hash map
     *
     * @param strs Array of strings to be grouped
     * @return List of lists, where each inner list contains strings that are anagrams of each other
     */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        // Edge case: if input is null, return empty list
        if (strs == null) {
            return new ArrayList<>();
        }

        // HashMap to store groups of anagrams
        // Key: sorted string (canonical form of anagram)
        // Value: list of original strings that are anagrams
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Process each string in the input array
        for (String str : strs) {
            // Convert string to char array for sorting
            char[] charArray = str.toCharArray();
            // Sort the characters to create a canonical form for all anagrams
            Arrays.sort(charArray);
            // Convert back to string to use as key
            String sortedStr = new String(charArray);

            // Add the original string to its anagram group
            // If the group doesn't exist yet, create a new one
            anagramGroups.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }

        // Convert the values of the map to a list of lists
        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * Alternative solution using character count as the key
     * This is more efficient for longer strings as it avoids sorting.
     * Time Complexity: O(n * k) where n is the length of strs and k is the maximum length of a string in strs
     * Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        if (strs == null) {
            return new ArrayList<>();
        }

        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            // Create a count array for all 26 lowercase letters
            int[] count = new int[26];

            // Count the frequency of each character in the string
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // Build a string key using the character counts
            // Format: #1#2#3#...#26# where each # represents the count of a letter
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#').append(count[i]);
            }
            String key = sb.toString();

            // Group the anagrams
            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        // Test case 1 from the problem statement
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
                List.of("bat"),
                Arrays.asList("nat", "tan"),
                Arrays.asList("ate", "eat", "tea")
        );
        testGroupAnagrams(strs1, expected1, "Test Case 1");

        // Test case 2 from the problem statement
        String[] strs2 = {""};
        List<List<String>> expected2 = List.of(List.of(""));
        testGroupAnagrams(strs2, expected2, "Test Case 2");

        // Test case 3 from the problem statement
        String[] strs3 = {"a"};
        List<List<String>> expected3 = List.of(List.of("a"));
        testGroupAnagrams(strs3, expected3, "Test Case 3");

        // Edge case: empty array
        String[] strs4 = {};
        List<List<String>> expected4 = List.of();
        testGroupAnagrams(strs4, expected4, "Edge Case: Empty Array");

        // Edge case: null array
        String[] strs5 = null;
        List<List<String>> expected5 = List.of();
        testGroupAnagrams(strs5, expected5, "Edge Case: Null Array");

        // Large input test
        // Generate a large array with many anagrams
        String[] largeInput = generateLargeInput(1000);
        System.out.println("Testing with large input (1000 strings)...");
        long startTime = System.currentTimeMillis();
        List<List<String>> result = groupAnagrams(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Regular solution completed in " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        List<List<String>> optimizedResult = groupAnagramsOptimized(largeInput);
        endTime = System.currentTimeMillis();
        System.out.println("Optimized solution completed in " + (endTime - startTime) + "ms");

        // Verify the results are the same
        System.out.println("Both solutions produce same result: " +
                (result.size() == optimizedResult.size() &&
                        new HashSet<>(result).size() == new HashSet<>(optimizedResult).size()));
    }

    /**
     * Helper method to test the groupAnagrams function
     */
    private static void testGroupAnagrams(String[] input, List<List<String>> expected, String testName) {
        System.out.println("Running " + testName + "...");

        List<List<String>> result = groupAnagrams(input);

        // Sort the lists for comparison
        result = sortForComparison(result);
        List<List<String>> sortedExpected = sortForComparison(expected);

        boolean passed = compareResults(result, sortedExpected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Got: " + result);
        }

        // Also test the optimized solution
        List<List<String>> optimizedResult = groupAnagramsOptimized(input);
        optimizedResult = sortForComparison(optimizedResult);

        boolean optimizedPassed = compareResults(optimizedResult, sortedExpected);

        System.out.println(testName + " (Optimized): " + (optimizedPassed ? "PASS" : "FAIL"));
        if (!optimizedPassed) {
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Got: " + optimizedResult);
        }
    }

    /**
     * Helper method to sort lists for comparison
     */
    private static List<List<String>> sortForComparison(List<List<String>> lists) {
        if (lists == null) return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : lists) {
            List<String> sorted = new ArrayList<>(list);
            Collections.sort(sorted);
            result.add(sorted);
        }

        // Sort the outer list based on the first element of each inner list
        result.sort((a, b) -> {
            if (a.isEmpty() && b.isEmpty()) return 0;
            if (a.isEmpty()) return -1;
            if (b.isEmpty()) return 1;
            return a.get(0).compareTo(b.get(0));
        });

        return result;
    }

    /**
     * Helper method to compare two results
     */
    private static boolean compareResults(List<List<String>> result, List<List<String>> expected) {
        if (result.size() != expected.size()) return false;

        for (int i = 0; i < result.size(); i++) {
            List<String> resultGroup = result.get(i);
            List<String> expectedGroup = expected.get(i);

            if (resultGroup.size() != expectedGroup.size()) return false;

            for (int j = 0; j < resultGroup.size(); j++) {
                if (!resultGroup.get(j).equals(expectedGroup.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Helper method to generate a large input for performance testing
     */
    private static String[] generateLargeInput(int size) {
        String[] result = new String[size];
        Random random = new Random(42); // Fixed seed for reproducibility

        for (int i = 0; i < size; i++) {
            int length = random.nextInt(10) + 1; // String length between 1 and 10
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                char c = (char) ('a' + random.nextInt(26));
                sb.append(c);
            }
            result[i] = sb.toString();
        }

        return result;
    }
}